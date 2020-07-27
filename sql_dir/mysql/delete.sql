delete from user t1 where t1.name>(select min(t2.name) from user t2 where t2.name=t1.name);

DELETE FROM user
WHERE 1=1
  AND `name` in (SELECT * FROM ( (SELECT `name` names FROM user GROUP BY name HAVING COUNT(*)>1) ) a)
  AND id not in (SELECT * FROM ( (SELECT MIN(id) ids FROM user GROUP BY NAME HAVING COUNT(`name`)>1) ) b);


delete from user
where 1=1 and
      name in (select * from ((select name from user group by name having count(*)>1) ) a) and
      id not in(select * from ((select min(id) from user group by name having count(*)>1))b);


select DISTINCT(name),u.* from `user` u where 1=1;

create procedure ts_proc()
        begin
            select * from user limit 5;
        end