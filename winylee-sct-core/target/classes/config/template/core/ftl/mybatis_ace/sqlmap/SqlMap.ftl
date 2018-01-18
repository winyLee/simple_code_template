<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackage}.${className}Dao">

    <resultMap id="${className}" type="${nameSpace}.${className}">
        <id column="id" property="id" />
		<#list  fields as field >
            <result column="${field.columnName}" property="${field.fieldId}" />
		</#list>
    </resultMap>

    <insert id="save" parameterType="${nameSpace}.${className}" useGeneratedKeys="true" keyProperty="id">
        insert into
        ${tblId}(
			<#list  fields as field >
				${field.columnName}<#if field_has_next>,</#if>
			</#list>
        )
        VALUES (
			<#list  fields as field >
                ${r'#{'}${field.fieldId}}<#if field_has_next>,</#if>
			</#list>
        )
        <selectKey resultType="long" keyProperty="id" order="AFTER" >
            SELECT LAST_INSERT_ID() as id
        </selectKey>
    </insert>

    <select id="delById" parameterType="Long" >
        DELETE FROM ${tblId}  where id = ${r'#{id}'}
    </select>

    <update id="updateById" parameterType="${nameSpace}.${className}" useGeneratedKeys="true" keyProperty="id">
        update     ${tblId} set
		<#list  fields as field >
			${field.columnName} = ${r'#{'}${field.fieldId}} <#if field_has_next>,</#if>
		</#list>
        where id = ${r'#{id}'}
    </update>

    <select id="findPage" resultMap="${className}" parameterType="java.util.Map">
        select *  from ${tblId}
        where 1=1
		<#list  fields as field >
           ${r'<if test="condition.'}${field.fieldId} != '' and  condition.${field.fieldId} != null">
            and ${field.columnName} = ${r'#{condition.'}${field.fieldId}}
          ${r'</if>'}
		</#list>
        <#if orderBy ? exists> ORDER BY ${orderBy} </#if>
   	    <#if orderByDesc ? exists> ${orderByDesc} </#if>
    </select>

    <select id="getById" resultMap="${className}" parameterType="Long">
        select *  from ${tblId} where id = ${r'#{id}'}
    </select>

</mapper>