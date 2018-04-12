对FreeMarker增加了模板继承功能,包含有 <@block> <@extends> <@override> <@super>
具体使用方法如下：

模板A,做为父模板:
<html>
<body>
<@block name="content">
this is super template
</@block>
</body>
</html>

模板B，作为模板继承模板A:
<@override name="content">
<@super/>
this is child template
</@override>
<@extends name="A模板路径"/>

此时子模板模板输出为
<html>
<body>
this is super template
this is child template
</body>
</html>