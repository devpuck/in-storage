
package com.wms.util;

/**
 * spring-boot-plus代码生成器入口类
 **/
public class SpringBootPlusGenerator {

    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        // 公共配置
        // 数据库配置
        codeGenerator
                .setUserName("C##WMS_IN_STORAGE")
                .setPassword("puck")
                .setDriverName("oracle.jdbc.OracleDriver")
                .setDriverUrl("jdbc:oracle:thin:@39.98.231.229:1521:ORCLCDB");

        // 包信息
        codeGenerator
                .setProjectPackagePath("com/wms")
                .setParentPackage("com.wms")
                .setCorePackage("com.xac.core");

        // 项目信息
        codeGenerator
                .setApiModuleName("wms-in-storage-api")
                .setBizModuleName("wms-in-storage-biz")
                .setTablePrefix("WMS_");


        // 组件作者等配置
        codeGenerator
                .setModuleName("config")
                .setAuthor("puck")
                .setPkIdColumnName("id");

        // 生成策略
        codeGenerator
                .setGeneratorStrategy(CodeGenerator.GeneratorStrategy.ALL)
                .setPageListOrder(true)
                .setParamValidation(true);

        // 生成实体映射相关代码,可用于数据库字段更新
        // 当数据库字段更新时，可自定义自动生成哪些那文件
        codeGenerator
                .setGeneratorEntity(true)
                .setGeneratorQueryParam(true)
                .setGeneratorQueryVo(true);

        // 生成业务相关代码
        codeGenerator
                .setGeneratorController(true)
                .setGeneratorService(true)
                .setGeneratorServiceImpl(true)
                .setGeneratorMapper(true)
                .setGeneratorMapperXml(true);

        // 是否生成Shiro RequiresPermissions注解
        codeGenerator.setRequiresPermissions(false);

        // 是否覆盖已有文件
        codeGenerator.setFileOverride(true);

        // 初始化公共变量
        codeGenerator.init();

        // 需要生成的表数组
        // xxx,yyy,zzz为需要生成代码的表名称
        String[] tables = {
                "CONFIG_SPEC_IN_WAREHOUSE"
        };

        // 循环生成
        for (String table : tables) {
            // 设置需要生成的表名称
            codeGenerator.setTableName(table);
            // 生成代码
            codeGenerator.generator();
        }

    }

}
