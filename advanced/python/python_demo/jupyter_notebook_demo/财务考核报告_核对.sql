/**估值能力start**/
        -- 根据股票代码查询所在行业的成分股 需要使用交易日查询
        WITH SUB_TICKER_SYMBOLS (TICKER_SYMBOL) AS (
            SELECT REGEXP_SUBSTR('300291,000967,603816', '[^,]+', 1, LEVEL) AS TICKER_SYMBOL
            FROM dual
            CONNECT BY REGEXP_SUBSTR('300291,000967,603816', '[^,]+', 1, LEVEL) IS NOT NULL
        )
        , SUB_INST_STOCK AS (
            SELECT t4.TYPE_ID, t4.TYPE_NAME, t5.TICKER_SYMBOL, t5.SEC_SHORT_NAME, T5.EXCHANGE_CD, t5.PARTY_ID
            FROM F_DATAYES.MD_INST_TYPE@source_db_link t3
            JOIN (
                SELECT t0.TICKER_SYMBOL, t0.EXCHANGE_CD, t1.TYPE_ID, t2.TYPE_NAME, t1.INTO_DATE, t1.OUT_DATE
                FROM F_DATAYES.MD_SECURITY@source_db_link T0
                JOIN F_DATAYES.MD_INST_TYPE@source_db_link T1 ON T0.PARTY_ID = T1.PARTY_ID
                JOIN F_DATAYES.MD_TYPE@source_db_link T2 ON T1.TYPE_ID = T2.TYPE_ID
                WHERE t2.INDUSTRY = '中信行业分类'   -- 中信行业分类, GICS行业分类, 中证行业, 申万行业分类(2021)分类   等
                AND T0.EXCHANGE_CD IN ('XSHE', 'XSHG', 'XBEI', 'XHKG')
                AND date '2024-03-31' >= T1.INTO_DATE
                AND date '2024-03-31' < COALESCE(T1.OUT_DATE, date '9999-12-31')
                AND t1.OUT_DATE IS NULL
        --         AND t1.IS_NEW = 1
                AND T0.ASSET_CLASS = 'E'
                AND TICKER_SYMBOL IN (SELECT TICKER_SYMBOL FROM SUB_TICKER_SYMBOLS)
                ORDER BY t1.INTO_DATE DESC
            ) t4 ON t3.TYPE_ID = t4.TYPE_ID
            JOIN F_DATAYES.MD_SECURITY@source_db_link t5 ON t3.PARTY_ID = t5.PARTY_ID
            WHERE t5.EXCHANGE_CD IN ('XSHE', 'XSHG', 'XBEI', 'XHKG')
        --     AND t3.IS_NEW = 1
            AND date '2024-03-31' >= t3.INTO_DATE
            AND date '2024-03-31' < COALESCE(t3.OUT_DATE, date '9999-12-31')
            AND t5.ASSET_CLASS = 'E'
              and t5.DY_USE_FLG = 1
              and LIST_STATUS_CD = 'L'
            AND t3.OUT_DATE IS NULL
        )
        -- 获取日期区间范围内每个季度最后一个交易日
        ,sub_quarter_dates AS (
            SELECT MAX(CALENDAR_DATE) AS CALENDAR_DATE
            FROM F_DATAYES.md_trade_cal@source_db_link
            WHERE EXCHANGE_CD = 'XSHG'
            AND IS_OPEN = 1
            AND CALENDAR_DATE BETWEEN date '2020-03-02' AND date '2024-03-31'
            GROUP BY EXTRACT(YEAR FROM CALENDAR_DATE),
            CASE
                WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 1 AND 3 THEN 1
                WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 4 AND 6 THEN 2
                WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 7 AND 9 THEN 3
                WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 10 AND 12 THEN 4
            END
        )
        , SUB_INST_STOCK_DATE AS (
            SELECT T1.*, AD.CALENDAR_DATE
            FROM SUB_INST_STOCK T1
            CROSS JOIN sub_quarter_dates AD
        )
        , SUB_RANK_SUBQUERY AS (SELECT t1.type_id,
                                      t1.type_name,
                                      t1.ticker_symbol,
                                      t1.sec_short_name,
                                      t1.exchange_cd,
                                      t1.CALENDAR_DATE,
                                      t3.TRADE_DATE,
                                      COALESCE(t3.DIV_RATE, 0)                                                                  AS DIV_RATE,
                                      RANK() OVER (PARTITION BY T1.TYPE_ID, T1.CALENDAR_DATE ORDER BY COALESCE(t3.DIV_RATE, 0)) AS rank,
                                      COUNT(*) OVER (PARTITION BY T1.TYPE_ID,T1.CALENDAR_DATE)                                     total
                               FROM SUB_INST_STOCK_DATE T1
                                        LEFT JOIN (SELECT t2.*
                                                   FROM F_DATAYES.mkt_rank_div_yield@source_db_link T2
                                                            JOIN sub_quarter_dates QD ON T2.TRADE_DATE = QD.CALENDAR_DATE) t3
                                                  ON T1.TICKER_SYMBOL = t3.TICKER_SYMBOL AND t1.CALENDAR_DATE = t3.TRADE_DATE
        )
        ,SUB_FINANCIAL_APPRAISAL AS (
            select type_id,
                   type_name,
                   ticker_symbol,
                   sec_short_name,
                   exchange_cd,
                   CALENDAR_DATE,
                   DIV_RATE,
                   rank_num,
                   rank_count,
                   total,
            --      如果是排名在第一并且存在重复值，则除以二再计算百分位
                   case when rank_count = 1 then rank_num / total else rank_count / 2 / total end div_rate_percent_rank
            from (SELECT type_id,
                         type_name,
                         ticker_symbol,
                         sec_short_name,
                         exchange_cd,
                         CALENDAR_DATE,
                         DIV_RATE,
                         total,
                         TRADE_DATE,
                         rank() OVER (PARTITION BY type_id, CALENDAR_DATE order by DIV_RATE) rank_num,
                         case
                             when DIV_RATE = 0 then
                                 count(rank) OVER (PARTITION BY type_id, CALENDAR_DATE, DIV_RATE order by DIV_RATE)
                             else 1 end                                                      rank_count
                  FROM sub_rank_subquery)
        )
        /**估值能力end**/

        /**成长能力start**/
        , sub_fdmt_is_n_q_pit as(
            SELECT N_INCOME_ATTR_P,REVENUE,END_DATE,PARTY_ID
        from (SELECT t.*,
                     ROW_NUMBER() OVER (PARTITION BY  PARTY_ID, END_DATE ORDER BY PUBLISH_DATE DESC) as rn
              FROM F_DATAYES.fdmt_is_n_q_pit@source_db_link t
              WHERE
                MERGED_FLAG = 1
              and END_DATE_REP <= date '2024-03-31'
              ) t1
        WHERE rn = 1
        ORDER BY t1.END_DATE DESC
        )
        , sub_revenue_diff AS (
            SELECT PARTY_ID,
                   END_DATE,
                   REVENUE - LAG(REVENUE, 4) OVER (PARTITION BY PARTY_ID ORDER BY END_DATE) AS revenue_diff
            FROM sub_fdmt_is_n_q_pit
            WHERE END_DATE > date '2020-03-02' - interval '2' year
              AND END_DATE <= date '2024-03-31'
        )
        ,sub_profit_diff AS (
            SELECT PARTY_ID,
                   END_DATE,
                   N_INCOME_ATTR_P - LAG(N_INCOME_ATTR_P, 4) OVER (PARTITION BY PARTY_ID ORDER BY END_DATE) AS profit_diff -- END_DATE循序排序，当前值-往前推的第4个值
            FROM sub_fdmt_is_n_q_pit
            WHERE END_DATE > date '2020-03-02' - interval '2' year
              AND END_DATE <= date '2024-03-31'
        )
        ,sub_std_dev AS (
            SELECT PARTY_ID,
                   END_DATE,
        -- 不能使用STDDEV，需要使用SQRT(VAR_POP计算总体标准差，跟excel中的STDEV.P保持一致
                   SQRT(VAR_POP(revenue_diff) OVER (PARTITION BY PARTY_ID ORDER BY END_DATE DESC ROWS BETWEEN CURRENT ROW AND 3 FOLLOWING)) AS revenue_stddev,
                   SQRT(VAR_POP(profit_diff) OVER (PARTITION BY PARTY_ID ORDER BY END_DATE DESC ROWS BETWEEN CURRENT ROW AND 3 FOLLOWING)) AS profit_stddev,
                   ROW_NUMBER() OVER (PARTITION BY PARTY_ID ORDER BY END_DATE desc) as rn
            FROM sub_revenue_diff
            JOIN sub_profit_diff USING (PARTY_ID, END_DATE)
            WHERE revenue_diff is not null and profit_diff is not null
        )
        ,sub_sur_sue_pre AS (
            SELECT t1.PARTY_ID,
                   sub_profit_diff.END_DATE,
                   case when revenue_stddev=0 then null else revenue_diff / revenue_stddev end AS SUR_PRE,
                   case when profit_stddev=0 then null else profit_diff / profit_stddev end AS SUE_PRE,
                   revenue_diff,
                   revenue_stddev,
                   profit_diff,
                   profit_stddev
--             FROM (select * from sub_std_dev where rn = 4) t1
            FROM sub_std_dev t1  --因为基于起始日期往前推了2年，前一年的4个季度是没有正常的方差
            JOIN sub_revenue_diff on t1.PARTY_ID = sub_revenue_diff.PARTY_ID and t1.END_DATE = sub_revenue_diff.END_DATE
            JOIN sub_profit_diff on sub_revenue_diff.PARTY_ID = sub_profit_diff.PARTY_ID and sub_profit_diff.END_DATE = sub_revenue_diff.END_DATE
            WHERE revenue_diff is not null and profit_diff is not null
        )
        -- 获取日期区间范围内每个季度最后一天
        ,sub_quarter_dates_normal AS (
            SELECT MAX(CALENDAR_DATE) AS CALENDAR_DATE
            FROM F_DATAYES.md_trade_cal@source_db_link
            WHERE EXCHANGE_CD = 'XSHG'
        --     AND IS_OPEN = 1
            AND CALENDAR_DATE BETWEEN date '2020-03-02' AND date '2024-03-31'
            GROUP BY EXTRACT(YEAR FROM CALENDAR_DATE),
            CASE
                WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 1 AND 3 THEN 1
                WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 4 AND 6 THEN 2
                WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 7 AND 9 THEN 3
                WHEN EXTRACT(MONTH FROM CALENDAR_DATE) BETWEEN 10 AND 12 THEN 4
            END
        )

        -- 避免漏掉没有数据的股票
        , SUB_INST_STOCK_DATE_NORMAL AS (
            SELECT T1.*, AD.CALENDAR_DATE
            FROM SUB_INST_STOCK T1
            CROSS JOIN sub_quarter_dates_normal AD
        )
        , SUB_GROWTH as (select tt.*,round(0.3 * SUR + 0.7 * SUE,6) AS growth_ability from (
        SELECT SN.*,
               sub_sur_sue_pre.END_DATE, sub_sur_sue_pre.SUR_PRE, sub_sur_sue_pre.SUE_PRE,sub_sur_sue_pre.revenue_diff,sub_sur_sue_pre.revenue_stddev,sub_sur_sue_pre.profit_diff,sub_sur_sue_pre.profit_stddev,
               CASE WHEN SUR_PRE IS NULL THEN NULL ELSE RANK() OVER (PARTITION BY SN.TYPE_ID, sub_sur_sue_pre.END_DATE ORDER BY SUR_PRE)
                                                            / COUNT(*) OVER (PARTITION BY SN.TYPE_ID, sub_sur_sue_pre.END_DATE) END AS SUR,
               CASE WHEN SUE_PRE IS NULL THEN NULL ELSE RANK() OVER (PARTITION BY SN.TYPE_ID, sub_sur_sue_pre.END_DATE ORDER BY SUE_PRE)
                                                            / COUNT(*) OVER (PARTITION BY SN.TYPE_ID, sub_sur_sue_pre.END_DATE) END AS SUE
        FROM SUB_INST_STOCK_DATE_NORMAL SN
        LEFT JOIN sub_sur_sue_pre ON SN.PARTY_ID = sub_sur_sue_pre.PARTY_ID AND SN.CALENDAR_DATE = sub_sur_sue_pre.END_DATE
        ORDER BY TYPE_ID, END_DATE DESC, TICKER_SYMBOL
        ) tt)
        /**成长能力end**/

        /*盈利能力start*/
        ,sub_roe_ooa AS
        (
          SELECT
           b.TICKER_SYMBOL,
           b.SEC_SHORT_NAME AS SEC_SHORT_NAME,
           f.type_id,
           a.PUBLISH_DATE, --报告修改过后END_DATE同样的值就会有2条记录
           a.END_DATE,
           a.N_CF_OPERATE_A as "现金流量净额",
           d.T_ASSETS as "总资产",
           --d.ROE as "ROE净资产收益率(平均)",
           e.ROE , --as "ROE净资产收益率",
           ROUND(a.N_CF_OPERATE_A/d.T_ASSETS,4) as OOA
          FROM
           F_DATAYES.fdmt_cf_indu_n_ttmp@source_db_link a  --全年的
          JOIN F_DATAYES.md_security@source_db_link b  ON a.PARTY_ID=b.PARTY_ID /*关联证券主表*/
          JOIN F_DATAYES.sys_code@source_db_link c ON a.MERGED_FLAG = c.VALUE_NUM_CD AND c.CODE_TYPE_ID = 70003 /*获取合并信息*/
          JOIN F_DATAYES.fdmt_indi_dupont_new@source_db_link d ON d.PARTY_ID=b.PARTY_ID and d.TICKER_SYMBOL=b.TICKER_SYMBOL
          JOIN F_DATAYES.fdmt_indi_rtn@source_db_link e ON e.PARTY_ID=b.PARTY_ID and e.END_DATE=a.END_DATE /*关联证券主表*/
          JOIN SUB_INST_STOCK f ON f.TICKER_SYMBOL=b.TICKER_SYMBOL /*去股票对应的type_id*/
          WHERE b.EXCHANGE_CD IN ('XSHG', 'XSHE', 'XBEI')
          AND b.ASSET_CLASS = 'E'   /*限制为沪深北股票*/
          AND b.TICKER_SYMBOL in(SELECT DISTINCT(TICKER_SYMBOL) FROM SUB_INST_STOCK WHERE type_id IN
          (SELECT TYPE_ID  FROM SUB_INST_STOCK WHERE TICKER_SYMBOL IN (SELECT TICKER_SYMBOL FROM SUB_TICKER_SYMBOLS))) /*输入需要查询的股票代码*/
          --AND a.END_DATE BETWEEN DATE'2023-01-31' AND DATE'2023-12-31' /*输入需要查询的报告期*/
          AND a.END_DATE BETWEEN date '2020-03-02' AND date '2024-03-31' /*输入需要查询的报告期*/
          AND d.END_DATE=a.END_DATE
          AND a.MERGED_FLAG=1
          AND a.IS_NEW=1   --如果存在修改报告，则相同的END_DATE会有多条记录，需要用它来获得最新的一条记录;
          ORDER BY a.END_DATE,f.type_id,b.TICKER_SYMBOL
        )
        --同财报日期股票数量
        ,sub_tmp_num AS
        (
          SELECT t1.END_DATE,t1.type_id,COUNT(t1.TICKER_SYMBOL) as rlt_num FROM sub_roe_ooa t1
          GROUP BY t1.END_DATE,t1.type_id
          ORDER BY t1.END_DATE
        )
        --股票的成长能力
        ,sub_ability AS
        (
          SELECT TICKER_SYMBOL,SEC_SHORT_NAME,PUBLISH_DATE,END_DATE,pf_ability --as "盈利能力"
          FROM
          (
            SELECT t3.*,
            ROUND((RANK() OVER (PARTITION BY t3.END_DATE,t3.TYPE_ID ORDER BY t3.pf_mid ASC NULLS LAST)/t3.rlt_num),6) as pf_ability
            FROM
            (
              SELECT t2.*,ROUND(0.3*t2.ROE_MID+0.7*t2.OOA_MID,4) as pf_mid
              FROM
              (
                  SELECT t1.*,tn.rlt_num,
                  ROUND((RANK() OVER (PARTITION BY t1.END_DATE,t1.TYPE_ID ORDER BY t1.ROE ASC NULLS LAST)/tn.rlt_num),6) as ROE_MID,
                  ROUND((RANK() OVER (PARTITION BY t1.END_DATE,t1.TYPE_ID ORDER BY t1.OOA ASC NULLS LAST)/tn.rlt_num),6) as OOA_MID
                  FROM sub_roe_ooa t1,sub_tmp_num tn
                  WHERE tn.END_DATE=t1.END_DATE
                  AND tn.TYPE_ID=t1.TYPE_ID
                  ORDER BY t1.END_DATE,t1.TYPE_ID,t1.TICKER_SYMBOL
              )t2
            )t3
            ORDER BY t3.END_DATE,t3.TICKER_SYMBOL
          )
        )
        /*盈利能力end*/

        ,SUB_SCORE AS (
        SELECT t1.TICKER_SYMBOL,t1.SEC_SHORT_NAME,t2.CALENDAR_DATE T_DATE,t1.DIV_RATE_PERCENT_RANK,t2.GROWTH_ABILITY
               ,t3.pf_ability PROFIT
             ,
               rank() over (partition by t1.TYPE_ID,TO_CHAR(t2.CALENDAR_DATE, 'YYYY-MM')
               order by 0.35*t1.DIV_RATE_PERCENT_RANK+0.25*t3.pf_ability+0.45*t2.growth_ability)
--                order by 0.35*t1.DIV_RATE_PERCENT_RANK+0.25+0.45*t2.growth_ability)
                    / COUNT(*) over (partition by t1.TYPE_ID,TO_CHAR(t2.CALENDAR_DATE, 'YYYY-MM')) AS SCORE
                from SUB_FINANCIAL_APPRAISAL t1
--             估值能力中的日期是季末的最后一个交易日
            join SUB_GROWTH t2 on t1.ticker_symbol = t2.ticker_symbol and TO_CHAR(t1.CALENDAR_DATE, 'YYYY-MM') = TO_CHAR(t2.CALENDAR_DATE, 'YYYY-MM')
            join sub_ability t3 on t3.ticker_symbol = t1.ticker_symbol and TO_CHAR(t1.CALENDAR_DATE, 'YYYY-MM') = TO_CHAR(t3.END_DATE, 'YYYY-MM')
         )
        SELECT TICKER_SYMBOL,SEC_SHORT_NAME,T_DATE,round(DIV_RATE_PERCENT_RANK,6) AS DIV_RATE_PERCENT_RANK
        ,round(PROFIT,6) AS PROFIT
        ,round(GROWTH_ABILITY,6) AS GROWTH_ABILITY
        ,round(SCORE,6) AS SCORE
            FROM SUB_SCORE where TICKER_SYMBOL in (SELECT TICKER_SYMBOL FROM SUB_TICKER_SYMBOLS)
                   ORDER BY TICKER_SYMBOL,T_DATE;