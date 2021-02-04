# graduation-process-management
#### 21-02-04
- 修复异常处理Controller不能支持Patch请求的错误
- 修复secretkey和salt为非16进制的bug (salt和secretkey不能为非16进制表示的字符串)
- 修复导入教师时, 密码为明文的bug (忘记加密)
#### 21-01-30

- 完成初始化教师功能

#### 21-01-29

- 简单完成了部分异常的统一捕获, 强制向用户抛出HTTP 200 状态码, 而通过ResultVO中的Code 显示真正请求的状态

- 事务测试(插入数据的是否抛出异常)
- 创建分支开发
- 修改励志输入位debug级别
- 书写Api文档相关

#### 21-01-28

- 修改了ResultVO 中 data 的数据类型
- 完善ReadMe文档
- 确定随机字符串的位数为10位

#### 21-01-25
- ~~修复了随机字符串位数由9位变成8位~~
- 对登陆功能进行测试
- 抽象Service层, 将简单业务逻辑写到Controller层

TODO
- 统一异常处理
#### 21-01-24 
初步完成登陆功能
- 封装ResultVO
- 封装加密, 生成`Authorized Token`, `json` 和 `map` 格式互转组件



