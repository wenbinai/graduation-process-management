# graduation-process-management

#### 21-06-20
- 解决了重复选择问题
- 解决了超卖问题
- 避免死锁导致服务端异常和事务回滚

#### 21-06-19
TODO
- 解决悲观锁死锁问题
- 解决重复选择问题
已完成
- 解决Group属性和Mysql中关键字`Group`冲突Bug
- 解决Redis缓存序列化`LocalDateTime`的Bug
- 添加Redis缓存

#### 21-06-18
- 修复删除单个学生和教师的Bug(原因: API命名重复)

#### 21-05-28

- 通过number的唯一索引优化批量导入用户是存在重复数据功能的效率

#### 21-05-24

- 利用课程上学到的知识对之前代码进行第一次大的重构
    - 包结构进行重新划分
    - ResultVO 进行重写
    - 添加了事务管理

- 添加了swagger3 进行项目API的管理

#### 21-02-17

- 解决mybatis雪花算法序列化id精度缺失问题

#### 21-02-15

- 添加了获取所有教师信息通用接口

#### 21-02-04

- 修改数据库连接池连接等待时间
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
- 添加Github Action

#### 21-01-24

初步完成登陆功能

- 封装ResultVO
- 封装加密, 生成`Authorized Token`, `json` 和 `map` 格式互转组件



