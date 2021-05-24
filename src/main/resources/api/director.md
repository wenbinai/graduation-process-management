导入学生名单。后端判断学号是否冲突。number已经加了唯一约束

post /api/director/students

required: number/name
not null: number/name/role/password/

[
  {
    "number": "2046204600",
    "name": "王小明",
    "clazz": "软件工程2018-01"
  }
]
修改角色

patch /api/director/teachers/{uid}/role

{
  "role": 4
}
更新教师基本信息，不包括重置密码

patch /api/director/teachers/{uid}/info

{
  "name": "",
  "title": ""
}
重置教师密码为number

put /api/director/teachers/{uid}/password

添加教师 post /api/director/teachers

required: number/name

{
  "number": "",
  "name": "",
  "title": "",
  "quantity": 0
}
统一更新全部教师带学生数

patch /api/director/teachers/quantity

[
  {
    "id": 1234567890,
    "quantity": 8
  }
]
return，全部教师信息。与get /api/common/teachers结构相同

[
  {
    "number": "1234567890",
    "name": "李琰",
    "title": "讲师",
    "quantity": 10
  }
]
删除指定教师

delete /api/director/teachers/{tid}

删除指定学生

delete /api/director/students/{sid}

--

创建选导师任务
所有导师所带学生数，必须与学生总数匹配，否则无法创建选导师任务。

post /api/director/tasks/choice

{
  "title": "选导师",
  "description": "毕业生在截至时间前，选择毕设指导教师",
  "startTime": "2021-02-20 08:00",
  "endTime": "2021-02-25 20:00"
}