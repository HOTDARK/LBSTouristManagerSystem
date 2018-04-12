<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.entity;
import com.hd.sfw.core.model.BaseModel;

<#include "/java_imports.include">
public class ${className} extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	<#list table.columns as column>
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	//columns END
	
	//用于查询的时间列
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	private ${column.javaType} ${column.columnNameLower}Begin;
	private ${column.javaType} ${column.columnNameLower}End;
	</#if>
	</#list>

<@generateConstructor className/>
<@generateJavaColumns/>

<#macro generateJavaColumns>
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	public void set${column.columnName}Begin(${column.javaType} value) {
		this.${column.columnNameLower}Begin = value;
	}
	
	public ${column.javaType} get${column.columnName}Begin() {
		return this.${column.columnNameLower}Begin;
	}
	public void set${column.columnName}End(${column.javaType} value) {
		this.${column.columnNameLower}End = value;
	}
	
	public ${column.javaType} get${column.columnName}End() {
		return this.${column.columnNameLower}End;
	}
	</#if>
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>
}