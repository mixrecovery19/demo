INSERT INTO country (name) VALUES ('Australia');
INSERT INTO country (name) VALUES ('India');
INSERT INTO country (name) VALUES ('Mongolia');

INSERT INTO CATEGORY
(NAME, DESCRIPTION)
VALUES
('Technology',
 'Programming and technology');

INSERT INTO CATEGORY
(NAME, DESCRIPTION)
VALUES
('History',
 'Historical information');

INSERT INTO CATEGORY
(NAME, DESCRIPTION)
VALUES
('Science',
 'Scientific content');

INSERT INTO CATEGORY
(NAME, DESCRIPTION)
VALUES
('Politics',
 'Political articles');

INSERT INTO person (
    username,
    password,
    familyname,
    givenname,
    is_admin,
    dob,
    biography,
    country_id
)
VALUES (
    '1',
    '$2a$10$Iz3HbIZnLcDi83uQ.0rjw.Pna.WW5.t0BbRR4r2MkPHO7j.LsfFPi',
    'Admin',
    'Admin',
    TRUE,
    '2001-01-01',
    'The Admin account serves as the principal administrator profile for the Wiki Content Management System and is responsible for overseeing, maintaining, and moderating the entire platform. Unlike standard user accounts, Admin possesses elevated privileges that provide unrestricted access to system functionality, including the ability to create, edit, review, and remove content across all sections of the wiki. This account exists primarily to demonstrate role-based permissions within the application and to support administration, testing, and quality control processes.
As the central management profile of the system, Admin is responsible for monitoring article activity, maintaining category organisation, reviewing content submissions, and ensuring that the wiki remains accurate, functional, and properly structured. The account has authority to access all user-created articles regardless of ownership, allowing for moderation and correction where necessary. In addition, Admin can oversee categories, update navigation structures, and maintain overall consistency across the website.
From a technical perspective, the Admin account demonstrates several key software engineering concepts used throughout the Spring Boot MVC application, including authentication, session handling, object relationships, access control, and data persistence through Spring Data JPA. The account also plays an important role in validating security measures, such as password hashing, restricted page access, and role-based functionality between ordinary users and administrators.
Within the context of this project, the Admin account acts as both a system supervisor and a testing profile for demonstrating the application during assessment. It provides a practical example of how privileged users can monitor content, maintain platform integrity, and ensure that the wiki remains operational and organised. Ultimately, Admin represents the central authority of the system—responsible for keeping the knowledge base accurate, secure, and accessible for all users.',
    1
);

INSERT INTO person (
    username,
    password,
    familyname,
    givenname,
    is_admin,
    dob,
    biography,
    country_id
)
VALUES (
    'student',
    'bit235',
    'Sharma',
    'Priya',
    FALSE,
    '2005-01-01',
    'Priya Sharma, affectionately called "Student" by her peers in university forums, was born in 2005 in Melbourne to Indian immigrant parents. A prodigious learner from childhood, Priya finished high school as dux of her year with an ATAR of 99.5 while simultaneously
completing advanced diplomas in data science.
Now a third-year student at the University of Melbourne pursuing a double degree in Computer Science and Environmental Science, Priya embodies the modern Renaissance student. She maintains a perfect GPA while serving as president of the university’s Sustainability Coding
Club, where she leads projects developing AI models to track urban biodiversity. Her research paper on machine learning applications for coral reef monitoring was published in an international journal at age nineteen.
Priya balances academics with community impact, tutoring underprivileged students in STEM subjects through a volunteer program she founded. She has completed internships at Google and CSIRO, earning recognition for innovative solutions in climate data visualization.
Despite a demanding schedule, Priya practices classical Indian dance and competes in hackathons, recently winning first place in the National AI for Good Challenge.
Known for her infectious enthusiasm and collaborative spirit, Priya inspires classmates with her growth mindset and resilience. She hopes to pursue a PhD focused on technology-driven environmental conservation. Priya Sharma represents the best of Generation Z—
technologically fluent, socially conscious, and determined to solve humanity’s greatest challenges while still finding time to enjoy late-night study sessions with friends and perfecting her mother’s butter chicken recipe.',
    2
);

INSERT INTO person (
    username,
    password,
    familyname,
    givenname,
    is_admin,
    dob,
    biography,
    country_id
)
VALUES (
    'genghis',
    'khan',
    'Khan',
    'Genghis',
    FALSE,
    '1162-01-01',
    'Genghis Khan, born Temüjin around 1162 in the harsh steppes of Mongolia, rose from humble and brutal beginnings to become one of history’s most formidable conquerors. After his father’s poisoning and his
family''s abandonment, young Temüjin endured slavery, starvation, and tribal warfare. Through sheer willpower, strategic brilliance, and charisma, he united the fractured Mongol tribes by 1206 and was
proclaimed Genghis Khan—“universal ruler.”
He revolutionized warfare with highly mobile horse archers, sophisticated intelligence networks, merit-based promotion, and psychological terror tactics. His Mongol Empire became the largest contiguous
land empire in history, stretching from Korea to Eastern Europe and encompassing China, Central Asia, and Persia. Genghis implemented religious tolerance, standardized trade along the Silk Road, and
created the first international postal system (Yam).
Estimates suggest his campaigns caused 20-40 million deaths, yet he also fostered cultural exchange and economic prosperity across Eurasia. Genghis Khan was a master administrator who codified laws in the
Yassa and promoted literacy among his people. He died in 1227 during a campaign against the Western Xia, possibly from injury or illness. His descendants, including Kublai Khan, expanded the empire
further.
Genghis remains a complex figure: a brutal warlord to some, a visionary unifier and military genius to others. In Mongolia, he is revered as the father of the nation. His genetic legacy is remarkable—
scientists estimate 16 million men today carry his Y-chromosome. Genghis Khan transformed the medieval world, redrawing maps and accelerating global connections centuries before the Age of Exploration. His
life exemplifies how one individual''s ambition and organizational genius can reshape human history.',
    3
);

INSERT INTO article (
    title,
    content,
    author_id
)
VALUES (
    'Admin User Account',
    'Number 1 is the admin user account.',
    1
);

INSERT INTO article (
    title,
    content,
    author_id
)
VALUES (
    'Priya Sharma Biography',
    'Priya Sharma, affectionately called Student by her peers...',
    2
);

INSERT INTO article (
    title,
    content,
    author_id
)
VALUES (
    'Genghis Khan Biography',
    'Genghis Khan, born Temujin around 1162...',
    3
);