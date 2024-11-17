select count(*) from public.problemcontent where content ='[]';
select * from public.problemcontent where content ='[]';
delete from public.problemcontent where content ='[]';

SELECT * FROM public.problems WHERE pid NOT IN ( SELECT pcid FROM public.problemcontent);
delete from public.problems where pid not in (select pcid from public.problemcontent);

select * from public.firms where cid not in (select pid from public.problems);
delete from public.firms where cid not in (select pid from public.problems);

select * from public.problemcontent WHERE content ~* 'LEETCODE';
UPDATE public.problemcontent SET content = regexp_replace(content, '(?i)LEETCODE', 'DevDojo', 'g');

select * from public.problems WHERE dev_dojo_link ~* 'https://leetcode.com/';
UPDATE public.problems SET dev_dojo_link = regexp_replace(dev_dojo_link, '(?i)https://leetcode.com', '', 'g');

