-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Май 24 2018 г., 06:39
-- Версия сервера: 10.2.12-MariaDB
-- Версия PHP: 7.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `id5861385_blog`
--

-- --------------------------------------------------------

--
-- Структура таблицы `blogs`
--

CREATE TABLE `blogs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `blogs`
--

INSERT INTO `blogs` (`id`, `user_id`, `title`) VALUES
(1, 6, 'Моя жизнь, мои правила. Вот так то!!!'),
(3, 57, 'блог пользователя : gdg'),
(4, 58, 'блог пользователя : sdfdsf'),
(5, 59, 'блог пользователя : wer'),
(6, 61, 'блог пользователя : sdfdsfds'),
(7, 62, 'блог пользователя : dsfsd'),
(8, 63, 'блог пользователя : sdfsd');

-- --------------------------------------------------------

--
-- Структура таблицы `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `record_id` int(11) NOT NULL,
  `text` varchar(700) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `comments`
--

INSERT INTO `comments` (`id`, `record_id`, `text`, `user_id`) VALUES
(1, 12, 'dfd', 6),
(2, 12, 'gf', 6),
(3, 12, 'gf', 6);

-- --------------------------------------------------------

--
-- Структура таблицы `records`
--

CREATE TABLE `records` (
  `id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` varchar(3000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `records`
--

INSERT INTO `records` (`id`, `blog_id`, `title`, `text`) VALUES
(12, 1, 'dsfdsfsd', 'sdfsd'),
(13, 1, 'в', 'в'),
(14, 1, 'Гороскопы', 'Друзья, привет!\r\nВы верите в гороскопы?\r\nВот и я нет. Я вообще мало во что верю. Но одна девушка Ирина Светлая меня заинтересовала своим предложением составить гороскоп карьеры. Предложение было бесплатным для первых 5 участников, и я, конечно, решила быть в этой пятерке.\r\nЭто был очень интересный тренинг по выбору ниши, плюс для каждого участника Ирина составила гороскоп карьеры. Вы знаете, я была настолько удивлена результатом, потому что гороскоп просто на 96% описывал меня. Да, у меня сильно развито \"Я сама всего добьюсь\". Да, я часто бываю недовольна собой, мне кажется, что я все делаю не так. Да, карьера для меня много значит. Да, мне важно получить признание общественности. И многое многое другое.\r\nЭто было действительно интересно.\r\n\r\nЕще один положительный момент мероприятия был в том, что появилась возможность пообщаться с новыми людьми, так же как и я, строящими свое новое будущее. Я рассказала о своих идеях, получила обратную связь. И вынесла для себя нечто важное. Мне необязатель');

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'admin'),
(2, 'client');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(250) NOT NULL,
  `fio` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `role_id`, `login`, `password`, `fio`) VALUES
(1, 1, 'admin', 'b3a7f1b293e33cbb14de7f4f861fe585', 'Админ Админов Админский'),
(6, 2, 'Shawn', 'b3a7f1b293e33cbb14de7f4f861fe585', 'Андрей'),
(56, 2, 'gg', 'b5eca75b93bac252dc0200ab59f40b59', 'grrgg'),
(57, 2, 'gdg', 'b5eca75b93bac252dc0200ab59f40b59', 'grrgg'),
(58, 2, 'sdfdsf', '9ad7254150e9f80ffea36ac27a87fe89', 'sdfdsf'),
(59, 2, 'wer', 'a23c99a9c72552e49a8ef4dbc6350048', 'wer'),
(61, 2, 'sdfdsfds', '8dc666b0a55ba59f2a0bea609cfec518', 'dfsd'),
(62, 2, 'dsfsd', 'bc231b515ae5c980b8e18586e47f77a3', 'sdfds'),
(63, 2, 'sdfsd', '8dc666b0a55ba59f2a0bea609cfec518', 'dsfds');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `blogs`
--
ALTER TABLE `blogs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Индексы таблицы `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`record_id`),
  ADD KEY `record_id` (`record_id`),
  ADD KEY `user_id_2` (`user_id`);

--
-- Индексы таблицы `records`
--
ALTER TABLE `records`
  ADD PRIMARY KEY (`id`),
  ADD KEY `blog_id` (`blog_id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `blogs`
--
ALTER TABLE `blogs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `records`
--
ALTER TABLE `records`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `blogs`
--
ALTER TABLE `blogs`
  ADD CONSTRAINT `blogs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`record_id`) REFERENCES `records` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `records`
--
ALTER TABLE `records`
  ADD CONSTRAINT `records_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
