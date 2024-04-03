if [ -n "$1" ];then
  new_sql_file=src/main/resources/db/migration/V$(date +%Y%m%d%H%I%S)__${1}.sql
  touch "$new_sql_file"
  echo "a new migration script generated at: ""$new_sql_file"
else
  echo "请输入迁移脚本名称"
fi
