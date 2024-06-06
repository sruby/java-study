import cx_Oracle
import pandas as pd
from dotenv import load_dotenv
import os
from .log import get_logger

class Database:
    def __init__(self, db_source='prod'):
        # Determine which .env file to load based on the ENV environment variable
        env = os.getenv('ENV', db_source)
        dotenv_file = f'../utils/.env.{env}'
        if os.path.isfile(dotenv_file):
            print(f"{dotenv_file} exists.")
        else:
            print(f"{dotenv_file} does not exist.")
        load_result = load_dotenv(dotenv_path=dotenv_file)
        print(f"load result: {load_result}")

        try:
            cx_Oracle.clientversion()
        except cx_Oracle.DatabaseError:
            print("cx_Oracle is not initialized.")
            cx_Oracle.init_oracle_client(lib_dir=os.getenv("LIB_DIR"))
        self.pool = cx_Oracle.SessionPool(user=os.getenv("DB_USER"), password=os.getenv("DB_PASSWORD"),
                                          dsn=os.getenv("DB_DSN"), min=2, max=10, increment=1)
        self.logger = get_logger(__name__)

    def execute_sql(self, sql_text, params=None):
        self.logger.info(f"Executing SQL: {sql_text}")
        connection = self.pool.acquire()
        cursor = connection.cursor()
        cursor.execute(sql_text, params)
        result = None
        if sql_text.strip().upper().startswith("SELECT"):
            result = cursor.fetchall()
        elif sql_text.strip().upper().startswith(("INSERT", "UPDATE", "DELETE")):
            result = cursor.rowcount
        connection.commit()
        cursor.close()
        self.pool.release(connection)
        return result

    def execute_sql_pd(self, sql_text, params=None):
        self.logger.info(f"Executing SQL: {sql_text},params: {params}")
        connection = self.pool.acquire()
        df = pd.read_sql(sql_text, con=connection, params=params)
        self.pool.release(connection)
        return df