INSERT INTO country (name) VALUES ('Australia');
INSERT INTO country (name) VALUES ('India');
INSERT INTO country (name) VALUES ('Mongolia');

INSERT INTO person (
    username,
    password,
    familyname,
    givenname,
    dob,
    biography,
    country_id
)
VALUES (
    '1',
    '$2a$10$Iz3HbIZnLcDi83uQ.0rjw.Pna.WW5.t0BbRR4r2MkPHO7j.LsfFPi',
    'Rivera',
    'Alex',
    '1985-01-01',
    'Alex Rivera, known simply as "Admin" in the digital corridors of TechNova Solutions, was
born in Manila in 1985 and immigrated to Australia at age twelve. From a young age, Alex displayed an extraordinary aptitude for technology, dismantling his first computer at thirteen and rebuilding it
with upgraded components by morning. After earning a Bachelor of Information Technology from the University of Technology Sydney, Alex began a career that would span two decades in systems architecture and
cybersecurity.
As lead administrator for one of Australia’s largest cloud infrastructure providers, Alex oversees networks that support millions of users daily. Colleagues describe Admin as the calm guardian of the
server room—methodical, quick-witted, and possessing an almost supernatural ability to diagnose failures before they occur. Beyond technical expertise, Alex mentors junior staff and has developed open-
source monitoring tools adopted by companies worldwide. In 2022, Alex received the Australian IT Professional of the Year award for implementing zero-trust security frameworks that thwarted several major
ransomware attempts.
Outside work, Admin is an avid hiker and amateur astronomer, often found stargazing in the Blue Mountains. Married with two children, Alex balances high-pressure on-call duties with family life, teaching
kids coding during weekends. At heart, Admin represents the invisible heroes of the digital age—those who keep the modern world running silently, one firewall rule at a time. With over 300,000 lines of
custom scripting under the belt and a reputation for turning crises into smooth operations, Alex Rivera continues to shape the backbone of Australia’s technological infrastructure.',
    1
);
INSERT INTO person (username, password, familyname, givenname, dob, biography, country_id)
VALUES ('student','bit235','Sharma','Priya','2005-01-01',
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
INSERT INTO person (username, password, familyname, givenname, dob, biography, country_id)
VALUES ('genghis','khan','Khan','Genghis','1162-01-01',
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