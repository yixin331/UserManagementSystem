package com.usermanagementsystem.management;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql:///managementdb";
        String username = "root";
        String password = "123456";
        String moduleName = "management";
        String mapperLocation = "D:\\Github\\UserManagementSystem\\management\\src\\main\\resources\\mapper\\" + moduleName;
        String tables = "t_user,t_role";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("helen")
                            //.enableSwagger()
                            //.fileOverride()
                            .outputDir("D:\\Github\\UserManagementSystem\\management\\src\\main\\java");
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.usermanagementsystem")
                            .moduleName(moduleName)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // table name
                            .addTablePrefix("t_"); // prefix
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
