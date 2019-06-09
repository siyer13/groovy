package com.siyer.merge

import org.yaml.snakeyaml.Yaml

class GenerateMergeQueries {

    static void main(String[] args) {
        generateStatement()
    }

    private static void generateStatement() {
        Yaml parser = new Yaml()
        def yamlentry = parser.load(("C:\\Users\\sridhari\\IdeaProjects\\CompareRecordCount\\src\\main\\groovy\\com\\siyer\\merge\\merge.yaml" as File).text)
        String landingSchema = ""
        String rawSchema = ""
        String tableName = ""
        StringBuilder joinCondition
        StringBuilder prevWhereCondition
        //StringBuilder updtWhereCondition
        try {
            def source = yamlentry['source']

            source.each {
                landingSchema = it.landing_schema
                rawSchema = it.raw_schema
                tableName = it.table_name
                StringBuilder selectStmt = new StringBuilder()
                String postfix = ""
                selectStmt.append("SELECT ")
                postfix = ""
                    joinCondition = new StringBuilder()
                    it.primary_key.each {
                        joinCondition.append(postfix)
                        joinCondition.append("orig.${it} = incr.${it}")
                        postfix = " AND "
                    }
              /*      String rightJoin = """ FROM ${schemaName}.${tableName}_temp orig
    RIGHT OUTER JOIN ${schemaName}.${tableName}_incr incr ON """
                    newWhereCondition = new StringBuilder()
                    newWhereCondition.append(" WHERE ")
                    postfix = ""
                    it.primary_key.each {
                        newWhereCondition.append(postfix)
                        newWhereCondition.append("orig.${it} IS NULL")
                        postfix = " AND "
                    }*/
                    String leftJoin = """\tFROM ${landingSchema}.${tableName}_merge orig \n  LEFT OUTER JOIN ${landingSchema}.${tableName}_incr incr \n ON  \n"""
                    prevWhereCondition = new StringBuilder()
                    prevWhereCondition.append("\nWHERE ")
                    postfix = ""
                    it.primary_key.each {
                        prevWhereCondition.append(postfix)
                        prevWhereCondition.append("incr.${it} IS NULL")
                        postfix = " AND "
                    }
                String finalPrevQuery  = "SELECT orig.* \n" + leftJoin + joinCondition.toString() + prevWhereCondition.toString()
                   /* String innerJoin = """ FROM ${schemaName}.${tableName}_temp orig
    JOIN ${schemaName}.${tableName}_incr incr ON """
                    updtWhereCondition = new StringBuilder()

                    updtWhereCondition.append(" WHERE incr.last_update_date  > orig.last_update_date  ")
                    String finalNewQuery  = "SELECT incr.* " + rightJoin + joinCondition.toString() + newWhereCondition.toString()

                    String finalUpdtQuery = "SELECT incr.* " + innerJoin + joinCondition.toString() + updtWhereCondition.toString()
                    /*println finalNewQuery
                    println finalPrevQuery
                    println finalUpdtQuery*/

                    String mergeQuery = "INSERT OVERWRITE TABLE ${rawSchema}.${tableName}\n" + finalPrevQuery + " \n UNION ALL \nSELECT * FROM ${landingSchema}.${tableName}_incr"
                 /*   File prevFile = new File(tableName+"_prev.sql")
                    File newFile = new File(tableName+"_new.sql")
                    File updtFile = new File(tableName+"_updt.sql")
                     prevFile.append(finalPrevQuery)
                     newFile.append(finalNewQuery)
                     updtFile.append(finalUpdtQuery)*/
                String createQuery = "CREATE TABLE sridharidb.${tableName}_merge AS SELECT * FROM ${rawSchema}.${tableName}"
                String dropQuery = "DROP TABLE sridharidb.${tableName}_merge"
              /*  File mergeQueryFile = new File("hql/merge/",tableName+".sql")
                File createTableQueryFile = new File("hql/create_merge_table/",tableName+".sql")
                File dropTableQueryFile = new File("hql/drop_merge_table/",tableName+".sql")
                mergeQueryFile.
                mergeQueryFile.append(mergeQuery)
                createTableQueryFile.append(createQuery)
                dropTableQueryFile.append(dropQuery)*/

                new File("hql/merge/",tableName+".sql").withWriter { writer ->
                    writer.write(mergeQuery)
                }
                new File("hql/create_merge_table/",tableName+".sql").withWriter { writer ->
                    writer.write(createQuery)
                }
                new File("hql/drop_merge_table/",tableName+".sql").with { writer ->
                    writer.write(dropQuery)
                }
            }

            }
        catch (Exception e) {
            println "something went wrong 2"
            println e


        }
    }

}
