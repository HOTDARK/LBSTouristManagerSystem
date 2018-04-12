<#include "/java_copyright.include">
<#assign className = table.className>   
package ${basepackage}.dao;
import com.hd.sfw.dao.mybatis.BaseMapper;
import ${basepackage}.entity.${className};

<#include "/java_imports.include">
public interface ${className}Mapper extends BaseMapper<${className},${table.idColumn.javaType}>{

}
