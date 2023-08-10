select count(id)
from users;-- US01 actual

select count(distinct id)
from users;-- US01 expected
-- uso1-02
select *
from users;

-- borrow book number
select *
from book_borrow
where is_returned = 0;

-- us03
select name
from book_categories;

select *
from books
where name = 'Slow Book';

select bc.name, count(*)
from book_borrow
         inner join books b on book_borrow.book_id = b.id
         inner join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;

select id,name,author from books
where name = 'Clean Code' and author='Robert C.Martin'
order by id desc;


select full_name,b.name,bb.borrowed_date from users u
inner join book_borrow bb on u.id = bb.user_id
inner join books b on bb.book_id = b.id
where full_name='Test Student 5' and name='FAst Fast'
order by 3 desc ;