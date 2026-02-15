-- data.sql 例
insert into project(id, title, summary, body_markdown, repo_url, app_url, published_at, updated_at)
values (1,'Sample','Summary','# Hello','https://github.com/...','https://app.example', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into project_tags(project_id, tags) values (1, 'java');
insert into project_tags(project_id, tags) values (1, 'spring');

insert into post(id, title, body_markdown, published, published_at, updated_at)
values (1, 'First post', '# Hello', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into post_tags(post_id, tags) values (1, 'spring');
insert into post_tags(post_id, tags) values (1, 'java');