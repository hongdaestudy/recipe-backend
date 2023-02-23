insert into recipe
values (1,
        sysdate(),
        sysdate(),
        '1',
        'N',
        '1234',
        'FIVE_MINUTES_LESS', -- coooking_time
        'EASY', -- difficulty_level
        'ONE', -- serving_count
        '123',
        '123',
        'IN_PROGRESS',
        '1',
        '1',
        '123');

insert into recipe_step values('1', sysdate(), sysdate(), '설명', '1234', '1', '1');
insert into recipe_step values('2', sysdate(), sysdate(), '설명', '1234', '2', '1');
insert into recipe_step values('3', sysdate(), sysdate(), '설명', '1234', '3', '1');

insert into recipe_tag values('1', sysdate(), sysdate(), '정선우', '0', '1');
insert into recipe_tag values('2', sysdate(), sysdate(), '정선우', '0', '1');