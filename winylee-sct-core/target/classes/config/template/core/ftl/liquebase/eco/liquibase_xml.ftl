<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd">

    <changeSet author="${authorName}" id="${tableCode}_${authorName}_1">
        <createTable tableName="${tableCode}" remarks="${tableName}">

            <column name="${pkField.code}" type="${pkField.dataTypeStr}" <#if autoIncrementKey>autoIncrement="true" startWith="1"</#if>  remarks="${tableName}">
                <constraints nullable="false" primaryKey="true" primaryKeyName="pk_${tableCode}"/>
            </column>

    <#list  cols as column >
        <#if !column.pkFlag>
            <column name="${column.code}" type="${column.dataTypeStr}" remarks="${column.comment}"/>
        </#if>
    </#list>

            <column name="creater" type="varchar(50)">
                <constraints nullable="false"/>
            </column>
            <column name="create_time" type="bigint">
                <constraints nullable="false"/>
            </column>
            <column name="modifier" type="varchar(50)">
                <constraints nullable="false"/>
            </column>
            <column name="modify_time" type="bigint">
                <constraints nullable="false"/>
            </column>
            <column name="version" type="bigint">
                <constraints nullable="false"/>
            </column>

        </createTable>
    </changeSet>

</databaseChangeLog>