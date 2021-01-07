
package com.wms.util.generator;

import com.baomidou.mybatisplus.generator.config.querys.OracleQuery;

/**
 * MySQL代码生成查询是否为空的列
 **/
public class SpringBootPlusMySqlQuery extends OracleQuery {

    @Override
    public String[] fieldCustom() {
        return new String[]{"db", "default"};
    }

}
