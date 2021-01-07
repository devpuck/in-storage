
package com.wms.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 */
@Data
@Accessors(chain = true)
public class CodeGenerator {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 驱动URL
     */
    private String driverUrl;

    /**
     * 生成的类路径
     */
    private String projectPackagePath;

    /**
     * 项目主包路径
     */
    private String parentPackage;


    /**
     * 项目公共主包路径
     */
    private String corePackage;

    /**
     * 包名称
     */
    private String packageController = "controller";

    // ############################ 自定义配置部分 start ############################
    /**
     * 模块名称
     */
    private String moduleName;

    private String apiModuleName;

    private String bizModuleName;

    /**
     * 表前缀如SYS_log 前缀为SYS_
     */
    private String tablePrefix;

    /**
     * 作者
     */
    private String author;
    /**
     * 生成的表名称
     */
    private String tableName;
    /**
     * 主键数据库列名称
     */
    private String pkIdColumnName = "id";
    /**
     * 代码生成策略 true：All/false:SIMPLE
     * 0. SIMPLE 生成最基本的代码
     * 1. NORMAL 生成普通代码
     * 2. ALL 生成所有的代码
     */
    private GeneratorStrategy generatorStrategy = GeneratorStrategy.ALL;

    /**
     * 生成策略
     */
    public enum GeneratorStrategy {
        SIMPLE, NORMAL, ALL
    }

    /**
     * 分页列表查询是否排序 true：有排序参数/false：无
     */
    private boolean pageListOrder = false;
    /**
     * 是否生成validation校验，true：生成/false：不生成
     */
    private boolean paramValidation = true;

    /**
     * 是否生成实体类
     */
    private boolean generatorEntity;
    /**
     * 是否生成控制器
     */
    private boolean generatorController;
    /**
     * 是否生成service接口
     */
    private boolean generatorService;
    /**
     * 是否生成service实现类
     */
    private boolean generatorServiceImpl;
    /**
     * 是否生成Mapper
     */
    private boolean generatorMapper;
    /**
     * 是否生成Mapper XML
     */
    private boolean generatorMapperXml;
    /**
     * 是否生成查询参数
     */
    private boolean generatorQueryParam;
    /**
     * 是否生成查询VO
     */
    private boolean generatorQueryVo;
    /**
     * 是否生成Shiro RequiresPermissions 注解
     */
    private boolean requiresPermissions;
    // ############################ 自定义配置部分 end ############################

    /**
     * 公共父包
     */
    private String commonParentPackage;

    /**
     * 实体父类
     */
    private String superEntity;
    /**
     * 控制器父类
     */
    private String superController;
    /**
     * service父接口
     */
    private String superService;
    /**
     * service实现父类
     */
    private String superServiceImpl;
    /**
     * 查询参数父类
     */
    private String superQueryParam;
    /**
     * 实体父类实体列表
     */
    private String[] superEntityCommonColumns;

    // 公共类包路径

    /**
     * 公共结果集路径
     */
    private String commonApiResult;

    /**
     * 公共排序查询参数
     */
    private String commonOrderQueryParam;
    /**
     * 公共分页对象
     */
    private String commonPaging;

    /**
     * 是否文件覆盖
     */
    private boolean fileOverride;

    /**
     * 初始化变量
     */
    public void init() {
        this.commonParentPackage = this.corePackage;
        // 父类包路径
        this.superEntity = this.commonParentPackage + ".entity.BaseEntity";
        this.superController = this.commonParentPackage + ".api.BaseController";
        this.superService = this.commonParentPackage + ".service.BaseService";
        this.superServiceImpl = this.commonParentPackage + ".service.impl.BaseServiceImpl";
        this.superQueryParam = this.commonParentPackage + ".api.QueryParam";
        this.superEntityCommonColumns = new String[]{"CREATED_BY","CREATED_DATE" ,"LAST_UPDATED_BY","LAST_UPDATED_DATE","DELETED_BY","DELETED_DATE","IS_DELETED"};

        // 公共类包路径
        this.commonApiResult = this.commonParentPackage + ".api.ApiResult";
        this.commonOrderQueryParam = this.commonParentPackage + ".api.SortQueryParam";
        this.commonPaging = this.commonParentPackage + ".api.Paging";
    }

    /**
     * 生成代码
     */
    public void generator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/"+ bizModuleName + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);                  // 是否打开输出目录
        gc.setSwagger2(true);               // 启用swagger注解
        gc.setIdType(IdType.AUTO);     // 主键类型:ID_WORKER
        gc.setServiceName("%sService");     // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setFileOverride(fileOverride);   // 是否覆盖已有文件
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型为Date
        gc.setBaseResultMap(true);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(driverUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        // 设置自定义查询
        // dsc.setDbQuery(new SpringBootPlusMySqlQuery());
        dsc.setDbType(DbType.ORACLE);


        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(parentPackage);
        pc.setController(packageController);

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

                String entityFullName = tableName.replace(tablePrefix,"");
                String camelEntityName = underlineToCamel(entityFullName);
                String pascalEntityName = underlineToPascal(entityFullName);

                String camelTableName = underlineToCamel(tableName);
                String pascalTableName = underlineToPascal(tableName);
                String colonTableName = underlineToColon(tableName);

                Map<String, Object> map = new HashMap<>();
                map.put("customField", this.getConfig().getGlobalConfig().getAuthor());
                // 查询参数包路径
                String queryParamPackage = parentPackage + StringPool.DOT + "api." + pc.getModuleName() ;
                map.put("queryParamPackage", queryParamPackage);
                // 查询参数类路径
                map.put("queryParamPath", queryParamPackage + StringPool.DOT + pascalEntityName + "QueryParam");
                // 查询参数共公包路径
                map.put("queryParamCommonPath", superQueryParam);

                // 响应结果包路径 com.xac.platform.api.file
                String queryVoPackage = parentPackage + StringPool.DOT + "api." + pc.getModuleName();
                map.put("queryVoPackage", queryVoPackage);
                // 响应结果类路径
                map.put("queryVoPath", queryVoPackage + StringPool.DOT + pascalEntityName + "Vo");

                // 响应结果包路径 com.xac.platform.biz.model.file
                String queryBoPackage = parentPackage + StringPool.DOT + "model.bo."+ pc.getModuleName();
                map.put("queryBoPackage", queryBoPackage);
                // 响应结果类路径
                map.put("queryBoPath", queryBoPackage + StringPool.DOT + pascalEntityName + "Bo");

                // entity 路径
                String entityPackage = parentPackage + StringPool.DOT + "model.entity";
                map.put("entityPackage", entityPackage);

                // mapper 路径
                String mapperPackage = parentPackage + StringPool.DOT + "mapper."+pc.getModuleName();
                map.put("mapperPackage", mapperPackage);

                // service 路径
                String servicePackage = parentPackage + StringPool.DOT + "service";
                map.put("servicePackage", servicePackage);

                // serviceImpl 路径
                String serviceImplPackage = parentPackage + StringPool.DOT + "service.impl";
                map.put("serviceImplPackage", serviceImplPackage);

                // controller 路径
                String controllerPackage = parentPackage + StringPool.DOT + "controller";
                map.put("controllerPackage", controllerPackage);

                // 实体对象小写名称
                map.put("entityObjectLowName", pc.getModuleName());
                // 实体对象名称
                map.put("entityObjectName", camelEntityName);
                // service对象名称
                map.put("serviceObjectName", camelEntityName + "Service");
                // mapper对象名称
                map.put("mapperObjectName", camelEntityName + "Mapper");
                // 主键ID列名
                map.put("pkIdColumnName", pkIdColumnName);
                // 主键ID驼峰名称
                map.put("pkIdCamelName", underlineToCamel(pkIdColumnName));
                // 导入分页类
                map.put("paging", commonPaging);

                // ApiResult
                map.put("apiResult", commonApiResult);
                // 分页列表查询是否排序
                map.put("pageListOrder", pageListOrder);
                // 导入排序查询参数类
                map.put("orderQueryParamPath", commonOrderQueryParam);
                // 代码生成策略
                map.put("generatorStrategy", generatorStrategy);
                // 代码Validation校验
                map.put("paramValidation", paramValidation);
                // 冒号连接的表名称
                map.put("colonTableName", colonTableName);
                // 是否生成Shiro RequiresPermissions注解
                map.put("requiresPermissions", requiresPermissions);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();

        // 生成mapper xml
        if (generatorMapperXml) {
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath+"/"+ bizModuleName + "/src/main/resources/mapper/" + pc.getModuleName()
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
        }

        // 自定义queryParam模板
        if (generatorQueryParam) {
            focList.add(new FileOutConfig("/templates/queryParam.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath +"/"+ apiModuleName  + "/src/main/java/" + projectPackagePath + "/api/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
                }
            });
        }

        // 自定义queryVo模板
        if (generatorQueryVo) {
            focList.add(new FileOutConfig("/templates/queryVo.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath +"/"+ apiModuleName  + "/src/main/java/" + projectPackagePath + "/api/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
                }
            });

            focList.add(new FileOutConfig("/templates/queryBo.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath +"/"+ bizModuleName  + "/src/main/java/" + projectPackagePath + "/model/bo/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Bo" + StringPool.DOT_JAVA;
                }
            });
        }

        if(generatorMapper){
            focList.add(new FileOutConfig("/templates/mapper.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath +"/"+ bizModuleName  + "/src/main/java/" + projectPackagePath + "/mapper/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
                }
            });
        }

        if(generatorEntity){
            focList.add(new FileOutConfig("/templates/entity.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath +"/"+ bizModuleName  + "/src/main/java/" + projectPackagePath + "/model/entity/" + tableInfo.getEntityName() + "Entity" + StringPool.DOT_JAVA;
                }
            });
        }

        if(generatorController){
            focList.add(new FileOutConfig("/templates/controller.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath +"/"+ bizModuleName  + "/src/main/java/" + projectPackagePath + "/controller/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
                }
            });
        }

        if(generatorService){
            focList.add(new FileOutConfig("/templates/service.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath +"/"+ bizModuleName  + "/src/main/java/" + projectPackagePath + "/service/" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
                }
            });

            focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath +"/"+ bizModuleName  + "/src/main/java/" + projectPackagePath + "/service/impl/" + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
                }
            });
        }

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模版生成配置，设置为空，表示不生成
        TemplateConfig templateConfig = new TemplateConfig();
        // xml使用自定义输出
        templateConfig.setXml(null);
        // 是否生成entity
        if (!generatorEntity) {

        }
        templateConfig.setEntity(null);
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        // 是否生成mapper
        templateConfig.setMapper(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(superEntity);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass(superController);
        strategy.setSuperServiceClass(superService);
        strategy.setSuperServiceImplClass(superServiceImpl);
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns(superEntityCommonColumns);
        strategy.setControllerMappingHyphenStyle(true);
        /**
         * 注意，根据实际情况，进行设置
         * 当表名称的前缀和模块名称一样时，会去掉表的前缀
         * 比如模块名称为user,表明为user_info,则生成的实体名称是Info.java,一定要注意
         */
        strategy.setTablePrefix(tablePrefix);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    /**
     * 下划线专程驼峰命名
     * sys_user --> sysUser
     *
     * @param underline
     * @return
     */
    public static String underlineToCamel(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.underlineToCamel(underline);
        }
        return null;
    }

    /**
     * 下划线转换成帕斯卡命名
     * sys_user --> SysUser
     *
     * @param underline
     * @return
     */
    public static String underlineToPascal(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(underline));
        }
        return null;
    }

    /**
     * 下划线转换成冒号连接命名
     * sys_user --> sys:user
     *
     * @param underline
     * @return
     */
    public static String underlineToColon(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            String string = underline.toLowerCase();
            return string.replaceAll("_", ":");
        }
        return null;
    }

}
