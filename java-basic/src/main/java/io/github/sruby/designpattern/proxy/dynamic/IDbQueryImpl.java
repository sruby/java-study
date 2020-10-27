package io.github.sruby.designpattern.proxy.dynamic;

/**
 * @date 2020/10/23 17:12
 */
public class IDbQueryImpl implements IDbQuery {
    @Override
    public String query() {
        return "query";
    }
}
