-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2024 at 11:07 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mindsmeet`
--

-- --------------------------------------------------------

--
-- Table structure for table `community_members`
--

CREATE TABLE `community_members` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `community_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `community_members`
--

INSERT INTO `community_members` (`id`, `created_at`, `updated_at`, `community_id`, `user_id`) VALUES
(1, '2024-12-20 16:41:56', '2024-12-20 16:41:56', 1, 32),
(2, '2024-12-20 16:41:56', '2024-12-20 16:41:56', 1, 34),
(3, '2024-12-20 18:40:20', '2024-12-20 18:40:20', 1, 35),
(4, '2024-12-21 12:04:00', '2024-12-21 12:04:00', 1, 28),
(5, '2024-12-21 12:15:28', '2024-12-21 12:15:28', 2, 31);

-- --------------------------------------------------------

--
-- Table structure for table `community_msg`
--

CREATE TABLE `community_msg` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `message` longtext DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `community_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `community_msg`
--

INSERT INTO `community_msg` (`id`, `created_at`, `message`, `updated_at`, `community_id`, `user_id`) VALUES
(1, '2024-12-20 22:09:10', 'Assalamualaikum', '2024-12-20 22:09:10', 1, 34),
(2, '2024-12-20 22:30:29', 'Walaikumsalam', '2024-12-20 22:30:29', 1, 31),
(3, '2024-12-21 11:15:03', 'Hey There', '2024-12-21 11:15:03', 1, 31),
(4, '2024-12-21 12:15:51', 'Assalamualaikum', '2024-12-21 12:15:51', 2, 31),
(5, '2024-12-21 12:34:18', 'Walaikumsalam', '2024-12-21 12:34:18', 1, 32),
(6, '2024-12-21 12:41:04', 'Something Happened ???', '2024-12-21 12:41:04', 1, 32);

-- --------------------------------------------------------

--
-- Table structure for table `community_mst`
--

CREATE TABLE `community_mst` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `name` varchar(255) DEFAULT NULL,
  `profile` longtext DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `community_mst`
--

INSERT INTO `community_mst` (`id`, `created_at`, `description`, `is_active`, `name`, `profile`, `updated_at`, `user_id`) VALUES
(1, '2024-12-20 15:33:07', 'full community for old bytebuddy app users ', NULL, 'ByteBuddy Old Users', '20Dec2024033306PM_logo.png', '2024-12-20 15:33:07', 31),
(2, '2024-12-20 17:54:14', 'Want to create a website for your business ??? please message us in community', 1, 'Tech Savvy Community', '20Dec2024055413PM_logo_irg.png', '2024-12-20 17:54:14', 34),
(3, '2024-12-20 18:05:07', 'New Community for ByteBuddy From Tech Savvy Solution', 1, 'ByteBuddy', '20Dec2024060506PM_bytebuddy_bw.png', '2024-12-20 18:05:07', 34);

-- --------------------------------------------------------

--
-- Table structure for table `community_reply`
--

CREATE TABLE `community_reply` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `message` longtext DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `community_msg_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `faq_answers`
--

CREATE TABLE `faq_answers` (
  `id` int(11) NOT NULL,
  `answer` longtext DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `is_accepted` tinyint(1) DEFAULT 0,
  `updated_at` datetime DEFAULT NULL,
  `faq_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `code` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `faq_answers`
--

INSERT INTO `faq_answers` (`id`, `answer`, `created_at`, `is_accepted`, `updated_at`, `faq_id`, `user_id`, `code`) VALUES
(5, 'Hello abc', '2024-12-15 11:13:46', 0, '2024-12-15 11:13:46', 2, 32, '	sbcd();'),
(6, 'Hello World', '2024-12-15 11:33:29', 0, '2024-12-15 11:33:29', 1, 32, 'getHello();'),
(7, 'hello world', '2024-12-15 11:40:34', 0, '2024-12-15 11:40:34', 3, 32, 'abc();'),
(8, 'Valid perspective from better person change your whole life.', '2024-12-15 21:42:21', 0, '2024-12-15 21:42:21', 18, 32, ''),
(9, 'ye raha solution', '2024-12-16 12:21:37', 0, '2024-12-16 12:21:37', 19, 32, 'here();'),
(10, 'Hello', '2024-12-18 19:27:12', 0, '2024-12-18 19:27:12', 7, 32, 'public static void main(String[] args){\r\n}'),
(11, 'no needed bro', '2024-12-18 19:29:34', 0, '2024-12-18 19:29:34', 7, 32, 'here();'),
(12, 'no need to call ', '2024-12-18 19:31:34', 0, '2024-12-18 19:31:34', 5, 31, '//here is full solution for this\r\ncallVoid();'),
(13, 'new comment for you ', '2024-12-18 19:32:52', 0, '2024-12-18 19:32:52', 5, 31, '<p:fileUpload \r\n    listener=\"#{uploadNotesBean.upload}\"\r\n    accept=\".pdf\"\r\n    mode=\"advanced\"\r\n    skinSimple=\"true\"\r\n    update=\"@form\" />\r\n'),
(14, 'hey there found another solution', '2024-12-18 19:34:09', 0, '2024-12-18 19:34:09', 5, 31, '<p:ajaxStatus onstart=\"PF(\'statusDialog\').show()\" onsuccess=\"PF(\'statusDialog\').hide()\" />\r\n'),
(15, 'You think you\'re doing not well so try below code', '2024-12-19 00:40:45', 0, '2024-12-19 00:40:45', 1, 34, 'public static void main(String[] args){\r\n  System.out.println(\"Hello World\");\r\n}'),
(16, 'this is full code', '2024-12-19 11:22:52', 0, '2024-12-19 11:22:52', 17, 31, 'public int getFactorial(Integer 2){\r\n  return 2*2;\r\n}'),
(17, 'Found new solution', '2024-12-21 12:53:54', 0, '2024-12-21 12:53:54', 19, 31, 'getYourQuery();');

-- --------------------------------------------------------

--
-- Table structure for table `faq_mst`
--

CREATE TABLE `faq_mst` (
  `id` int(11) NOT NULL,
  `code` longtext DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `que` longtext DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `faq_mst`
--

INSERT INTO `faq_mst` (`id`, `code`, `created_at`, `description`, `que`, `updated_at`, `user_id`) VALUES
(1, 'SEVERE:   java.lang.TypeNotPresentException: Type org.primefaces.extensions.model.monacoeditor.EditorOptions not present\r\n	at java.base/sun.reflect.generics.factory.CoreReflectionFactory.makeNamedType(CoreReflectionFactory.java:117)\r\n	at java.base/sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:125)\r\n	at java.base/sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)\r\n	at java.base/sun.reflect.generics.visitor.Reifier.reifyTypeArguments(Reifier.java:68)\r\n	at java.base/sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:138)\r\n	at java.base/sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)\r\n	at java.base/sun.reflect.generics.repository.ClassRepository.computeSuperclass(ClassRepository.java:104)\r\n	at java.base/sun.reflect.generics.repository.ClassRepository.getSuperclass(ClassRepository.java:86)\r\n	', '2024-11-22 23:42:38', 'got this error while clean and build in java 8 ee project', 'How to solve this java error in netbeans 21 ?', '2024-11-22 23:42:38', 21),
(2, 'SEVERE:   java.lang.TypeNotPresentException: Type org.primefaces.extensions.model.monacoeditor.EditorOptions not present\r\n	at java.base/sun.reflect.generics.factory.CoreReflectionFactory.makeNamedType(CoreReflectionFactory.java:117)\r\n	at java.base/sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:125)\r\n	at java.base/sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)\r\n	at java.base/sun.reflect.generics.visitor.Reifier.reifyTypeArguments(Reifier.java:68)\r\n	at java.base/sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:138)\r\n	at java.base/sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)\r\n	at java.base/sun.reflect.generics.repository.ClassRepository.computeSuperclass(ClassRepository.java:104)\r\n	at java.base/sun.reflect.generics.repository.ClassRepository.getSuperclass(ClassRepository.java:86)\r\n	', '2024-11-22 23:44:27', 'got this error', 'How to solve ?', '2024-11-22 23:44:27', 21),
(3, 'avca', '2024-11-22 23:47:57', 'sfdasdfasdf ', 'sdfsad', '2024-11-22 23:47:57', 21),
(4, 'sdfsf', '2024-11-22 23:49:13', 'fdsf', 'gj kjh lkj', '2024-11-22 23:49:13', 21),
(5, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:42', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:42', 21),
(6, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:49', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:49', 21),
(7, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:49', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:49', 21),
(8, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:50', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:50', 21),
(9, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:50', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:50', 21),
(10, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:50', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:50', 21),
(11, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:57', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:57', 21),
(12, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:57', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:57', 21),
(13, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:57', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:57', 21),
(14, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:58', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:58', 21),
(15, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:46:59', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:46:59', 21),
(16, 'public void submitComment() {\r\n        FaqAnswers ans = new FaqAnswers();\r\n        ans.setAnswer(answer);\r\n        ans.setCode(code);\r\n        ans.setIsAccepted(false);\r\n        ans.setFaqId(faq);\r\n//        Response res = api.answerFaq(ans, Response.class);\r\n        Response res = fbl.answerFaq(ans);\r\n        \r\n        try{\r\n        FacesContext.getCurrentInstance().addMessage(null,\r\n                new FacesMessage(FacesMessage.SEVERITY_ERROR, res.toString(), null));\r\n        }catch(Exception e){\r\n            e.printStackTrace();\r\n        }\r\n    }', '2024-11-26 21:47:01', 'this is the full details of faq.', 'Hey there I\'m getting this exception', '2024-11-26 21:47:01', 21),
(17, '', '2024-12-15 21:38:58', 'need full programmatic implementation of how to calculate factorial in java', 'How to calculate Factorial in Java ?', '2024-12-15 21:38:58', 31),
(18, '', '2024-12-15 21:41:40', 'no need to learn AI ', 'need to know how the realworld programs works?', '2024-12-15 21:41:40', 32),
(19, 'INFO:   JWTAuthenticationMechanism - Token Created\r\nINFO:   JWTAuthenticationMechanism - Token Created\r\nINFO:   JWTAuthenticationMechanism - Token Created\r\nINFO:   JWTAuthenticationMechanism - Token Created\r\nINFO:   JWTAuthenticationMechanism - Token Created\r\n\r\nget();', '2024-12-16 12:21:06', 'this is fuill description in the jwt implementations.', 'got an error in jwt auth ?', '2024-12-16 12:21:06', 32);

-- --------------------------------------------------------

--
-- Table structure for table `faq_screenshot`
--

CREATE TABLE `faq_screenshot` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `faq_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `faq_votes`
--

CREATE TABLE `faq_votes` (
  `id` int(11) NOT NULL,
  `code` longtext DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `faq_ans_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_up_vote` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `group_mst`
--

CREATE TABLE `group_mst` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `group_mst`
--

INSERT INTO `group_mst` (`id`, `created_at`, `type`, `updated_at`) VALUES
(1, '2024-11-05 07:19:40', 'User', '2024-11-05 07:19:40'),
(2, '2024-11-05 07:19:49', 'Admin', '2024-11-05 07:19:49');

-- --------------------------------------------------------

--
-- Table structure for table `group_users`
--

CREATE TABLE `group_users` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `group_users`
--

INSERT INTO `group_users` (`id`, `created_at`, `updated_at`, `group_id`, `user_id`) VALUES
(2, '2024-11-06 11:28:44', '2024-11-06 11:28:44', 1, 21),
(3, '2024-11-14 13:19:53', '2024-11-14 13:19:53', 1, 22),
(8, '2024-11-14 18:50:21', '2024-11-14 18:50:21', 1, 27),
(9, '2024-11-14 18:58:22', '2024-11-14 18:58:22', 1, 28),
(10, '2024-11-14 19:19:18', '2024-11-14 19:19:18', 1, 29),
(11, '2024-11-14 19:27:38', '2024-11-14 19:27:38', 1, 30),
(12, '2024-11-25 20:51:34', '2024-11-25 20:51:34', 1, 31),
(13, '2024-12-13 13:04:56', '2024-12-13 13:04:56', 1, 32),
(15, '2024-12-15 22:30:09', '2024-12-15 22:30:09', 1, 34),
(16, '2024-12-16 07:28:59', '2024-12-16 07:28:59', 2, 35);

-- --------------------------------------------------------

--
-- Table structure for table `msg_mst`
--

CREATE TABLE `msg_mst` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_read` tinyint(1) DEFAULT 0,
  `message` longtext DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `msg_mst`
--

INSERT INTO `msg_mst` (`id`, `created_at`, `image`, `is_read`, `message`, `updated_at`, `receiver_id`, `sender_id`) VALUES
(1, '2024-12-17 10:52:32', NULL, 0, 'Assalamualaikum', '2024-12-17 10:52:32', 32, 31),
(2, '2024-12-17 11:11:35', NULL, 0, 'Walaikumsalam', '2024-12-17 11:11:35', 31, 32),
(3, '2024-12-17 11:18:23', NULL, 0, 'How Are You ???', '2024-12-17 11:18:23', 32, 31),
(4, '2024-12-17 11:18:55', NULL, 0, 'I\'m Fine What About You ?', '2024-12-17 11:18:55', 31, 32),
(5, '2024-12-17 11:20:33', NULL, 0, 'Alhamdulillah Bro !!!', '2024-12-17 11:20:33', 32, 31),
(6, '2024-12-17 11:34:10', NULL, 0, 'What\'s Going on bro ???', '2024-12-17 11:34:10', 31, 32),
(7, '2024-12-17 11:34:33', NULL, 0, 'Developing Chat sections', '2024-12-17 11:34:33', 32, 31),
(8, '2024-12-17 11:34:50', NULL, 0, 'Ohh!!! that\'s wonderful ... Bro', '2024-12-17 11:34:50', 31, 32),
(9, '2024-12-17 11:35:12', NULL, 0, 'Thanks Bro ❤️', '2024-12-17 11:35:12', 32, 31),
(10, '2024-12-17 11:54:30', NULL, 0, 'Your Most Welcome Bro', '2024-12-17 11:54:30', 31, 32),
(11, '2024-12-17 12:08:23', NULL, 0, 'Assalamualaikum', '2024-12-17 12:08:23', 34, 31),
(12, '2024-12-17 12:11:00', NULL, 0, 'Walaikumsalam', '2024-12-17 12:11:00', 31, 34),
(13, '2024-12-17 12:11:28', NULL, 0, 'How Are you Brother', '2024-12-17 12:11:28', 34, 31),
(14, '2024-12-17 12:11:38', NULL, 0, 'I\'m Fine What About you ?', '2024-12-17 12:11:38', 31, 34),
(15, '2024-12-17 12:11:48', NULL, 0, 'Alhamdulillah !!!', '2024-12-17 12:11:48', 34, 31),
(16, '2024-12-17 12:58:05', NULL, 0, 'Hey', '2024-12-17 12:58:05', 32, 31),
(17, '2024-12-17 12:58:55', NULL, 0, 'What\'s Going On ?', '2024-12-17 12:58:55', 31, 34),
(18, '2024-12-17 12:59:10', NULL, 0, 'Developing Chat App', '2024-12-17 12:59:10', 34, 31),
(19, '2024-12-17 12:59:25', NULL, 0, 'Ohh !! that\'s great brother', '2024-12-17 12:59:25', 31, 34),
(20, '2024-12-17 12:59:35', NULL, 0, 'Yeah !! thanks', '2024-12-17 12:59:35', 34, 31),
(21, '2024-12-17 13:21:31', NULL, 0, 'Your Most Welcome!!!', '2024-12-17 13:21:31', 31, 34),
(22, '2024-12-17 13:23:05', NULL, 0, 'Yeah Bro', '2024-12-17 13:23:05', 34, 31),
(23, '2024-12-17 13:27:23', NULL, 0, 'Hellooooo', '2024-12-17 13:27:23', 31, 34),
(24, '2024-12-17 13:50:38', NULL, 0, 'Hey', '2024-12-17 13:50:38', 34, 31),
(25, '2024-12-17 13:50:40', NULL, 0, 'Hey', '2024-12-17 13:50:40', 34, 31),
(26, '2024-12-17 13:50:47', NULL, 0, 'Hey', '2024-12-17 13:50:47', 34, 31),
(27, '2024-12-17 13:50:51', NULL, 0, 'Hey', '2024-12-17 13:50:51', 34, 31),
(28, '2024-12-17 13:51:26', NULL, 0, 'Hey Bro', '2024-12-17 13:51:26', 31, 34),
(29, '2024-12-17 13:52:19', NULL, 0, 'Hey', '2024-12-17 13:52:19', 34, 31),
(30, '2024-12-17 23:12:24', NULL, 0, 'Assalamualaikum', '2024-12-17 23:12:24', 31, 34),
(31, '2024-12-17 23:12:32', NULL, 0, 'Walaikumsalam', '2024-12-17 23:12:32', 34, 31),
(32, '2024-12-18 11:53:20', NULL, 0, 'Hello', '2024-12-18 11:53:20', 34, 31),
(33, '2024-12-18 19:16:37', NULL, 0, 'Hey Bro', '2024-12-18 19:16:37', 31, 32),
(34, '2024-12-20 12:22:38', NULL, 0, 'Salam', '2024-12-20 12:22:38', 31, 34),
(35, '2024-12-20 12:22:54', NULL, 0, 'Walaikumsalam', '2024-12-20 12:22:54', 34, 31),
(36, '2024-12-20 12:23:07', NULL, 0, 'How Are You ??', '2024-12-20 12:23:07', 34, 31),
(37, '2024-12-20 12:23:11', NULL, 0, 'Hello', '2024-12-20 12:23:11', 31, 34),
(38, '2024-12-20 12:23:50', NULL, 0, 'Done', '2024-12-20 12:23:50', 34, 31);

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE `notes` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `is_commentable` tinyint(1) DEFAULT 0,
  `is_listenable` tinyint(1) DEFAULT 0,
  `is_public` tinyint(1) DEFAULT 0,
  `is_translatable` tinyint(1) DEFAULT 0,
  `is_verified` tinyint(1) DEFAULT 0,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notes`
--

INSERT INTO `notes` (`id`, `created_at`, `description`, `file`, `is_commentable`, `is_listenable`, `is_public`, `is_translatable`, `is_verified`, `title`, `updated_at`, `user_id`) VALUES
(1, '2024-11-11 19:40:42', 'Dummy description', 'C:/Dummy/path', 1, NULL, NULL, 1, 0, 'Sample Note', '2024-11-11 19:40:42', 21),
(2, '2024-11-12 11:39:48', 'Dummy description', 'C:/Dummy/path', 1, NULL, NULL, 1, 0, 'Sample Note', '2024-11-12 11:39:48', 21),
(3, '2024-11-15 21:48:27', 'sample description', '15Nov2024094819PM_Unit_3.pdf', 1, NULL, 1, 1, 0, 'Dummy Title', '2024-11-15 21:48:27', 21),
(4, '2024-12-14 12:04:34', 'this is the official notes for achieving the higher grades in Masters.', '14Dec2024120151PM_unit_1.pdf', 1, NULL, 1, 1, 0, 'This is Sample Testing notes', '2024-12-14 12:04:34', 31),
(5, '2024-12-14 12:04:36', 'this is the official notes for achieving the higher grades in Masters.', '14Dec2024120151PM_unit_1.pdf', 1, NULL, 1, 1, 0, 'This is Sample Testing notes', '2024-12-14 12:04:36', 31),
(6, '2024-12-14 12:04:40', 'this is the official notes for achieving the higher grades in Masters.', '14Dec2024120151PM_unit_1.pdf', 1, NULL, 1, 1, 0, 'This is Sample Testing notes', '2024-12-14 12:04:40', 31),
(7, '2024-12-15 22:34:20', 'detailed discussion', '15Dec2024103417PM_SAHIL.pdf', 1, NULL, 1, 1, 0, 'My Note Unit 1', '2024-12-15 22:34:20', 34),
(12, '2024-12-18 19:15:25', 'asdfasdf', '18Dec2024071515PM_MSc_ICT_1Sem_All_Subject_Marks.pdf', 1, NULL, 1, 1, 0, 'asdfas', '2024-12-18 19:15:25', 32),
(13, '2024-12-18 19:20:15', 'this is feedback page', '18Dec2024072012PM_HK_Feedback.pdf', 1, NULL, 1, 1, 0, 'HK Feedback', '2024-12-18 19:20:15', 32),
(14, '2024-12-18 19:23:30', 'asdfa', '18Dec2024072328PM_stats_installs_installs_com.wbvf.wbvf_app_202411_overview.pdf', 1, NULL, 1, 1, 0, 'Some New Operations', '2024-12-18 19:23:30', 32),
(15, '2024-12-18 21:38:57', 'hey there new test', '18Dec2024093855PM_stats_installs_installs_com.wbvf.wbvf_app_202411_overview.pdf', 1, NULL, 1, 1, 0, 'Some New Operations', '2024-12-18 21:38:57', 31);

-- --------------------------------------------------------

--
-- Table structure for table `notes_text`
--

CREATE TABLE `notes_text` (
  `id` int(11) NOT NULL,
  `content` longtext DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `page_no` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `notes_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notes_text`
--

INSERT INTO `notes_text` (`id`, `content`, `created_at`, `page_no`, `updated_at`, `notes_id`) VALUES
(1, 'M.SHAKIL PATEL\r\n3. Type of Service (ToS): Used for specifying the priority of the datagram.\r\n4. Total Length: The size of the entire datagram, including both the header and\r\npayload.\r\n5. Identification, Flags, Fragment Offset: Used for fragmentation and\r\nreassembly of datagrams.\r\n6. TTL (Time to Live): Prevents infinite looping by limiting the number of hops a\r\ndatagram can make before being discarded.\r\n7. Protocol: Specifies the transport protocol used (e.g., TCP, UDP).\r\n8. Source and Destination IP Addresses: Indicates where the packet originated\r\nfrom and where it is headed.\r\n3.5.2 Fragmentation\r\nFragmentation occurs when an IP datagram is too large to be transmitted in one\r\npiece over a network with a smaller Maximum Transmission Unit (MTU). The\r\ndatagram is broken into smaller fragments that can be reassembled at the\r\ndestination.\r\nHow FragmentationWorks:\r\n● MTU: Every network has an MTU that defines the largest possible datagram\r\nsize that can be transmitted. For example, Ethernet has an MTU of 1500 bytes.\r\n● When an IP datagram exceeds the MTU, it is split into fragments.\r\n● Each fragment contains its own IP header with additional fields to help with\r\nreassembly.\r\n○ The Identification field is used to group fragments belonging to the\r\nsame original datagram.\r\n○ The Fragment Offset indicates the position of the fragment in the\r\noriginal datagram.\r\n○ TheMore Fragments (MF) flag tells whether more fragments are\r\ncoming (1 for more, 0 for the last fragment).\r\nReassembly: At the destination, fragments are reassembled back into the original\r\ndatagram. If any fragment is missing or corrupted, the entire datagram is discarded.\r\n8\r\n', '2024-11-15 21:48:23', 8, '2024-11-15 21:48:23', 3),
(2, 'M.SHAKIL PATEL\r\n● Example: 192.168.1.0/24\r\n○ The /24 indicates the first 24 bits are used for the network, and the\r\nremaining 8 bits are for host identification.\r\nClassless addressing is more flexible than classful addressing as it allows IP\r\naddresses to be allocated according to the actual need for network and host\r\nportions.\r\n3.1.2.1 Variable Length Blocks\r\nVariable-Length Subnet Masking (VLSM) allows subnets of different sizes, providing\r\ngreater flexibility and efficient use of IP address space. Using VLSM, subnets can have\r\ndifferent subnet masks, enabling more precise control of the network size.\r\nFor instance, a company may need:\r\n● 1000 addresses for its main office\r\n● 200 for branch offices\r\n● 20 for small remote sites.\r\nVLSM allows the network administrator to allocate these addresses without wasting\r\nlarge blocks of IPs.\r\n3.1.2.2 Subnetting in Classless Addressing\r\nIn classless addressing, subnetting involves manipulating the subnet mask in a\r\nflexible manner, often referred to as CIDR blocks.\r\nFor example, consider the IP range 192.168.1.0/24:\r\n● /25 subnet mask divides this into two subnets (192.168.1.0/25 and\r\n192.168.1.128/25), each supporting 128 hosts.\r\n● /26 divides it further, resulting in four subnets, each supporting 64 hosts.\r\n3\r\n', '2024-11-15 21:48:22', 3, '2024-11-15 21:48:22', 3),
(3, 'M.SHAKIL PATEL\r\nExtension Headers in IPv6\r\nIPv6 uses extension headers to provide optional features. These headers are placed\r\nbetween the IPv6 header and the transport layer (TCP/UDP) header. Extension\r\nheaders improve the protocol’s flexibility and allow for the addition of new features\r\nwithout modifying the base protocol.\r\nCommon Extension Headers:\r\n1. Hop-by-HopOptions: Contains information that must be examined by every\r\nrouter on the path.\r\n2. Routing: Specifies a list of routers the packet must visit.\r\n3. Fragment: Manages fragmentation of packets, as IPv6 routers do not perform\r\nfragmentation.\r\n4. Destination Options: Contains options that are only processed by the\r\ndestination node.\r\n5. Authentication Header (AH): Provides data integrity and authentication for\r\nthe entire packet.\r\n6. Encapsulating Security Payload (ESP): Provides encryption, ensuring the\r\nconfidentiality of the payload.\r\nSummary\r\nIPv6 addresses many of the limitations of IPv4, offering a vast address space,\r\nimproved security, auto-configuration features, and better efficiency in packet\r\nprocessing. While the transition from IPv4 is ongoing, IPv6 ensures the scalability and\r\nfuture growth of the internet. The simplified header and use of extension headers\r\nmake IPv6 more flexible and capable of handling the demands of modern\r\nnetworking environments.\r\n19\r\n', '2024-11-15 21:48:23', 19, '2024-11-15 21:48:23', 3),
(4, 'M.SHAKIL PATEL\r\n3. Routing Efficiency: Classless routing protocols can summarize routes, which\r\nreduces the size of routing tables and improves the performance of routers.\r\nClassful routing does not support this, leading to larger routing tables.\r\nIPv4 and IPv6 in tabular format:\r\nFeature IPv4 IPv6\r\nAddress Length 32-bit (4 bytes) 128-bit (16 bytes)\r\nAddress Format Dotted decimal (e.g.,\r\n192.168.1.1)\r\nHexadecimal colon-separated (e.g.,\r\n2001:0db8:85a3::8a2e:0370:733\r\n4)\r\nNumber of\r\nAddresses\r\nApproximately 4.3\r\nbillion (2^32)\r\nApproximately 340 undecillion (2^128)\r\nAddress Classes Supports classful\r\naddressing (A, B, C, D,\r\nE) and classless\r\n(CIDR)\r\nOnly supports classless addressing\r\n(CIDR)\r\nSubnetting Uses subnet masks\r\n(e.g.,\r\n255.255.255.0)\r\nUses prefix length (e.g., /64) for\r\nsubnetting\r\nHeader Size 20 to 60 bytes\r\n(variable)\r\n40 bytes (fixed)\r\nHeader\r\nComplexity\r\nMore complex,\r\nincludes fields like\r\nchecksum, options\r\nSimplified header, fields like checksum\r\nand options are removed\r\n22\r\n', '2024-11-15 21:48:23', 22, '2024-11-15 21:48:23', 3),
(5, 'M.SHAKIL PATEL\r\n● SLAAC allows a device to automatically configure its IPv6 address without the\r\nneed for a DHCP server.\r\n● When a device connects to a network, it can generate a link-local address\r\nbased on its MAC address using EUI-64 format, and it can also configure a\r\nglobal unicast address by communicating with a router to obtain the network\r\nprefix.\r\n● Duplicate Address Detection (DAD) is used to ensure that no two devices on\r\nthe same network use the same address.\r\nRenumbering:\r\n● IPv6 simplifies network renumbering, making it easier to change an entire\r\nnetwork\'s addressing scheme.\r\n● IPv6 routers can advertise new network prefixes, allowing devices to\r\nautomatically reconfigure their addresses without manual intervention.\r\n3.9.4 Transition from IPv4 to IPv6\r\nThe transition from IPv4 to IPv6 is necessary due to the exhaustion of IPv4 addresses.\r\nSeveral techniques have been developed to ensure smooth interoperability between\r\nthe two protocols during this transition phase.\r\nTransitionMechanisms:\r\n1. Dual-Stack: This is the most common approach, where both IPv4 and IPv6 are\r\nimplemented simultaneously on devices. A device will use IPv6 if available,\r\nfalling back to IPv4 if necessary.\r\n2. Tunneling: IPv6 packets are encapsulated inside IPv4 packets, allowing them\r\nto be transmitted over IPv4-only networks.\r\n○ Example: 6to4 and Teredo are popular tunneling techniques.\r\n3. NAT64: Network Address Translation between IPv6 and IPv4 allows IPv6\r\ndevices to communicate with IPv4-only devices by translating IPv6 addresses\r\ninto IPv4 addresses.\r\n17\r\n', '2024-11-15 21:48:23', 17, '2024-11-15 21:48:23', 3),
(6, 'M.SHAKIL PATEL\r\nFragmentation Performed by routers\r\nand the sender\r\nPerformed only by the sender, routers\r\ndo not fragment packets\r\nSecurity Security is optional,\r\nrelies on external\r\nprotocols like IPSec\r\nSecurity is built-in, IPSec is mandatory\r\nBroadcast Supports broadcast\r\ncommunication\r\nDoes not support broadcast, uses\r\nmulticast instead\r\nMulticast Supported Supported with better efficiency\r\nAddress\r\nConfiguration\r\nRequires manual\r\nconfiguration or DHCP\r\nSupports stateless auto-configuration\r\n(SLAAC) and stateful DHCPv6\r\nNAT (Network\r\nAddress\r\nTranslation)\r\nWidely used due to\r\nlimited address space\r\nNo need for NAT because of the vast\r\naddress space\r\nRouting Routing tables are\r\nlarger due to\r\nfragmentation and\r\nvariable header sizes\r\nSimplified and more efficient routing due\r\nto streamlined headers and no\r\nfragmentation\r\nMobile IP Complicated, requires\r\nadditional support\r\nEasier to implement with built-in mobility\r\nfeatures\r\nQuality of Service\r\n(QoS)\r\nQoS support limited,\r\nbased on Type of\r\nService (ToS) field\r\nEnhanced QoS, using Flow Label field\r\nfor priority handling\r\nDNS (Domain\r\nName System)\r\nIPv4 addresses are\r\nmapped using A\r\nrecords\r\nIPv6 addresses are mapped using\r\nAAAA records\r\n23\r\n', '2024-11-15 21:48:23', 23, '2024-11-15 21:48:23', 3),
(7, 'M.SHAKIL PATEL\r\nDevice\r\nInvolvement\r\nPhysical\r\ntransmission\r\nhardware (e.g.,\r\nNICs, cables,\r\nhubs, repeaters)\r\nSwitches, Bridges,\r\nNetwork Interface Cards\r\n(NICs)\r\nRouters, Layer 3\r\nSwitches\r\nAddressing No addressing,\r\ndeals with raw\r\nsignals and\r\nvoltages\r\nUses MAC addresses\r\nfor identifying devices on\r\nthe same network\r\nsegment\r\nUses IP\r\naddresses for\r\nidentifying devices\r\nacross different\r\nnetworks\r\nError Handling None, purely\r\nconcerned with\r\nsending raw data\r\nError detection and\r\ncorrection via\r\nmechanisms like CRC\r\n(Cyclic Redundancy\r\nCheck)\r\nMay handle errors\r\nrelated to packet\r\ndelivery (such as\r\ntimeouts) but not\r\nerror correction\r\nFlow Control None, no\r\nmechanism to\r\ncontrol the data\r\nrate\r\nImplements flow control\r\nmechanisms like\r\nstop-and-wait or\r\nsliding window\r\nMay handle\r\ncongestion control\r\nthrough protocols\r\nlike ICMP\r\nTransmission\r\nMode\r\nHandles the actual\r\nphysical\r\ntransmission (e.g.,\r\nvoltage levels,\r\nmodulation)\r\nDefines how data is\r\nformatted into frames for\r\ntransmission, including\r\nstart and stop bits\r\nDeals with how\r\ndata packets are\r\nrouted and\r\nforwarded from\r\nsource to\r\ndestination\r\nProtocols No specific\r\nprotocols,\r\nhardware-level\r\nstandards like\r\nEthernet (IEEE\r\n802.3), Wi-Fi\r\n(IEEE 802.11),\r\netc.\r\nEthernet, PPP\r\n(Point-to-Point Protocol),\r\nFrame Relay, HDLC\r\n(High-Level Data Link\r\nControl)\r\nIP (Internet\r\nProtocol), ICMP\r\n(Internet Control\r\nMessage\r\nProtocol), RIP,\r\nOSPF, BGP\r\n26\r\n', '2024-11-15 21:48:24', 26, '2024-11-15 21:48:24', 3),
(8, 'M.SHAKIL PATEL\r\nThis type of subnetting is used in modern networks to efficiently allocate IP addresses\r\naccording to the size of each subnet.\r\n3.2 Special IP Addresses\r\nCertain IP addresses serve specific purposes and cannot be assigned to hosts:\r\n● Network Address:\r\n○ Identifies a network. Example: In 192.168.1.0/24, the address\r\n192.168.1.0 is the network address.\r\n● Broadcast Address:\r\n○ Sends data to all hosts in a network. Example: In 192.168.1.0/24,\r\n192.168.1.255 is the broadcast address.\r\n● Private IP Addresses:\r\n○ These addresses are used for internal network communication and are\r\nnot routable over the internet:\r\n■ Class A: 10.0.0.0 to 10.255.255.255\r\n■ Class B: 172.16.0.0 to 172.31.255.255\r\n■ Class C: 192.168.0.0 to 192.168.255.255\r\n● Loopback Address:\r\n○ 127.0.0.1 is used to test the local network stack of a device.\r\n● APIPA (Automatic Private IP Addressing):\r\n○ Range: 169.254.0.0 to 169.254.255.255\r\n○ Assigned automatically by the OS when DHCP is unavailable.\r\n3.3 Delivery, Formatting, and Routing\r\n● Delivery: The network layer is responsible for delivering packets to the correct\r\ndestination, either within the same network (direct delivery) or to a different\r\nnetwork (indirect delivery).\r\n● Formatting (Encapsulation): Data from the transport layer is encapsulated\r\ninto IP packets. The packet consists of the IP header and the payload (data).\r\n4\r\n', '2024-11-15 21:48:22', 4, '2024-11-15 21:48:22', 3),
(9, 'M.SHAKIL PATEL\r\nnetwork into smaller pieces, reducing the number of devices in each segment and\r\nlimiting broadcast traffic.\r\n● Figure: (Multiple subnets, each with their own routing)\r\n+--------------+ +--------------+\r\n| Router A | | Router B |\r\n| 192.168.1.1 |---------------------| 192.168.2.1 |\r\n+--------------+ +--------------+\r\n| 192.168.1.0/24 | 192.168.2.0/24\r\n| |\r\n+--------------------+ +--------------------+\r\n| Device 1 (1.2) | | Device 2 (2.2) |\r\n+--------------------+ +--------------------+\r\n● Key Characteristics:\r\n1. Network 192.168.0.0/24 is divided into two subnets: 192.168.1.0/24\r\nand 192.168.2.0/24.\r\n2. Devices in each subnet are part of their own broadcast domain.\r\n3. More efficient forwarding: Routers only forward packets to the correct\r\nsubnet, reducing unnecessary broadcast traffic.\r\n4. Easier networkmanagement: Subnets can be allocated to different\r\ndepartments or sections of an organization.\r\n● Forwarding Process:\r\n1. Device 1 in subnet 192.168.1.0/24 wants to communicate with Device\r\n2 in subnet 192.168.2.0/24.\r\n2. Router A recognizes that Device 2 is not part of the local subnet\r\n(192.168.1.0/24) and forwards the packet to Router B, which\r\nmanages the 192.168.2.0/24 subnet.\r\n3. Router B then forwards the packet to Device 2.\r\nComparison of ForwardingWith andWithout Subnetting\r\nAspect Without Subnetting With Subnetting\r\n30\r\n', '2024-11-15 21:48:24', 30, '2024-11-15 21:48:24', 3),
(10, 'M.SHAKIL PATEL\r\nby spoofing registration messages, so authentication mechanisms must be\r\nrobust.\r\n4. Latency in Handoff: When a mobile device moves between foreign networks, it\r\nmust re-register its care-of address with the home agent. This registration\r\nprocess introduces latency, during which packets might be lost or delayed.\r\n3.9 Introduction to IPv6\r\nIPv6 (Internet Protocol Version 6) is the latest version of the Internet Protocol,\r\ndesigned to replace IPv4. IPv6 addresses the limitations of IPv4, especially the limited\r\naddress space, and introduces several enhancements, such as simplified packet\r\nheaders, improved security, and better support for mobile devices.\r\n3.9.1 Representation of IPv6\r\nIPv6 addresses are 128 bits long, significantly larger than the 32-bit IPv4 addresses.\r\nThis allows for a virtually unlimited number of unique IP addresses.\r\nIPv6 Address Notation:\r\n● An IPv6 address is written as eight groups of four hexadecimal digits,\r\nseparated by colons (:).\r\n● Example: 2001:0db8:85a3:0000:0000:8a2e:0370:7334\r\nSimplification of IPv6 Address:\r\n1. Omitting Leading Zeros: You can omit leading zeros in each block.\r\n○ Example: 2001:0db8::0001 becomes 2001:db8::1.\r\n2. Consecutive Zero Blocks: A double colon (::) can be used to replace\r\nconsecutive blocks of zeroes, but it can only be used once per address.\r\n○ Example: 2001:0db8:0000:0000:0000:0000:1428:57ab becomes\r\n2001:db8::1428:57ab.\r\n15\r\n', '2024-11-15 21:48:23', 15, '2024-11-15 21:48:23', 3),
(11, 'M.SHAKIL PATEL\r\n● Membership Query: Sent by routers to discover active multicast groups on the\r\nnetwork.\r\n● Membership Report: Sent by hosts to inform the router that they want to join a\r\nmulticast group.\r\n● Leave Group: Sent by hosts when they no longer wish to receive multicast\r\ntraffic from a specific group (IGMPv2 and later).\r\nHow IGMPWorks:\r\n● When a host wants to join a multicast group, it sends an IGMP membership\r\nreport message to the router.\r\n● The router, in turn, forwards multicast traffic from the requested group to the\r\nhost.\r\n● When the host no longer needs the multicast data, it sends a \"Leave Group\"\r\nmessage, and the router stops forwarding traffic for that group.\r\nIGMP Use Cases:\r\n● Multicast Streaming: Used in applications like live video streaming, where\r\ndata is broadcast to multiple recipients at once.\r\n● Online Gaming: Multiplayer games use multicast to efficiently send game\r\nupdates to multiple players simultaneously.\r\n● IPTV: Television services over IP networks rely heavily on IGMP for channel\r\nselection and group-based streaming.\r\nComparison of ICMP and IGMP\r\nFeature ICMP IGMP\r\nPurpose Error reporting and diagnostics Multicast group\r\nmanagement\r\nUsage To report network errors,\r\nunreachable hosts, etc.\r\nTo manage membership in\r\nmulticast groups\r\n34\r\n', '2024-11-15 21:48:24', 34, '2024-11-15 21:48:24', 3),
(12, 'M.SHAKIL PATEL\r\nThe Internet Control Message Protocol (ICMP) is a crucial network layer protocol\r\nthat is used for sending error messages and operational information regarding the\r\ndelivery of IP packets. It is a fundamental part of the IP protocol suite and helps in\r\ndiagnosing network communication issues.\r\nKey Features of ICMP:\r\n1. Error Reporting: ICMP helps in reporting errors like unreachable destinations,\r\ntime exceeded, or route failures.\r\n2. Diagnostic Tool: It is widely used in network diagnostic tools like ping and\r\ntraceroute, helping in determining if a host is reachable or tracing the path a\r\npacket takes.\r\n3. Does not deliver data: Unlike TCP and UDP, ICMP is not used for actual data\r\ntransfer between systems.\r\n4. Used by Routers and Hosts: Both routers and hosts use ICMP to communicate\r\nerror information or status updates.\r\nICMPMessage Types:\r\n● Echo Request & Reply: Used by the ping command to check if a system is\r\nreachable.\r\n● Destination Unreachable: Indicates that a packet cannot reach its destination\r\n(e.g., network/host unreachable, protocol/port unreachable).\r\n● Time Exceeded: Used when the Time-to-Live (TTL) value of a packet expires.\r\n● Redirect: Informs a host to use a different router for the next hop.\r\n● Source Quench: Indicates that the receiver is overwhelmed with data and\r\nrequests the sender to slow down transmission.\r\nHow ICMPWorks:\r\n● ICMP is encapsulated within IP packets.\r\n● For example, when using the ping command, an ICMP Echo Request is sent to\r\nthe destination, and if reachable, the destination replies with an ICMP Echo\r\nReply.\r\nCommon ICMP Use Cases:\r\n32\r\n', '2024-11-15 21:48:24', 32, '2024-11-15 21:48:24', 3),
(13, 'M.SHAKIL PATEL\r\nMobile IP Use Cases\r\n● Mobile Devices: Smartphones, tablets, and laptops that move between\r\nnetworks (e.g., from a home Wi-Fi to a mobile data network) while maintaining\r\nseamless communication.\r\n● Vehicular Networks: Cars or trains with embedded networking devices that\r\nmove across different networks while staying connected to the internet.\r\n● Telemedicine: Mobile doctors or medical equipment that require real-time\r\ncommunication across different network environments.\r\nConclusion\r\nMobile IP is a critical protocol for supporting mobility in the internet by allowing\r\ndevices to move across networks without changing their IP addresses. It maintains a\r\ncontinuous connection through agent discovery, registration, and tunneling\r\nprocesses. However, inefficiencies such as triangular routing and encapsulation\r\noverhead need to be addressed to improve its performance.\r\n40\r\n', '2024-11-15 21:48:24', 40, '2024-11-15 21:48:24', 3),
(14, 'M.SHAKIL PATEL\r\n3.6 ICMP (Internet Control Message Protocol)\r\nICMP (Internet Control Message Protocol) is used by network devices, like routers, to\r\nsend error messages and operational information about network connectivity. ICMP\r\nis an integral part of IP, but it does not transport user data; rather, it handles the\r\nnetwork-layer signaling that provides feedback on network issues.\r\nKey Features of ICMP:\r\n● Error Reporting: ICMP reports errors when datagrams cannot reach their\r\nintended destination. These messages help identify network problems.\r\n● Control Messages: It provides feedback about the state of the network,\r\nhelping with diagnostics and troubleshooting.\r\nCommon ICMPMessages:\r\n1. Echo Request and Echo Reply: Used by the ping command to test network\r\nconnectivity.\r\n2. Destination Unreachable: Sent when a router cannot forward a packet to its\r\ndestination.\r\n3. Time Exceeded: Generated when the TTL (Time To Live) field of a packet\r\nexpires, usually due to looping in the network.\r\n4. Redirect: Used by routers to inform a host of a better route for sending traffic.\r\n3.7 IGMP (Internet GroupManagement Protocol)\r\nIGMP (Internet GroupManagement Protocol) is used for managing multicast group\r\nmemberships. Multicast is a method where one sender can send data to multiple\r\nreceivers efficiently, often used in streaming video, gaming, and real-time\r\ncommunications.\r\n11\r\n', '2024-11-15 21:48:23', 11, '2024-11-15 21:48:23', 3),
(15, 'M.SHAKIL PATEL\r\nExample IP\r\nAddresses\r\nClass A: 10.0.0.0/8, Class\r\nB: 172.16.0.0/16, Class C:\r\n192.168.1.0/24\r\nFlexible notation:\r\n192.168.1.0/28,\r\n172.16.0.0/12\r\nDefault Subnet\r\nMask\r\nUses default subnet masks\r\nbased on the class (e.g., Class\r\nA = 255.0.0.0, Class B =\r\n255.255.0.0)\r\nSubnet mask is determined by\r\nthe CIDR prefix length (e.g., /28\r\n= 255.255.255.240)\r\nScalability Not very scalable; addresses\r\nare exhausted faster\r\nHighly scalable; supports\r\nefficient use of IP address space\r\nBroadcast Defined classes make\r\nbroadcasts easier to calculate\r\nCalculating broadcast addresses\r\ndepends on the subnet size\r\nRouting Table\r\nSize\r\nLarger routing tables, as routes\r\ncannot be summarized\r\nSmaller routing tables, thanks to\r\nroute aggregation (supernetting)\r\nAdoption Used in the early stages of the\r\nInternet (before 1993)\r\nAdopted as the standard\r\naddressing model (after 1993)\r\nExample Subnet\r\nCalculation\r\nClass B: 172.16.0.0/16 with\r\nsubnet mask 255.255.255.0\r\nfor 254 hosts\r\n172.16.0.0/18 with a subnet\r\nmask 255.255.192.0 for\r\n16,382 hosts\r\nKey Differences Explained:\r\n1. Address Allocation: In classful addressing, the IP address is divided into fixed\r\nclasses, leading to inefficient use of IP addresses. Classless addressing (CIDR)\r\nallows variable-length subnetting, making the allocation more efficient and\r\nreducing wastage.\r\n2. Subnetting and Flexibility: Classful addressing has rigid boundaries and\r\nrequires more complex subnetting when the default class subnet is\r\ninsufficient. Classless addressing allows network administrators to define\r\nnetwork sizes more precisely with VLSM.\r\n21\r\n', '2024-11-15 21:48:23', 21, '2024-11-15 21:48:23', 3),
(16, 'M.SHAKIL PATEL\r\nMobile IP Addressing\r\nMobile IP involves two key types of IP addresses:\r\n● HomeAddress: The permanent address that stays the same regardless of\r\nwhere the mobile node moves.\r\n● Care-of Address (CoA): The temporary address that changes depending on\r\nthe foreign network the mobile node is visiting.\r\nAgents inMobile IP\r\nThere are two key agents in Mobile IP:\r\n1. HomeAgent (HA):\r\n○ Located in the mobile node’s home network.\r\n○ Maintains the mapping between the mobile node’s home address and\r\nits current care-of address.\r\n○ Forwards packets to the mobile node when it is in a foreign network by\r\ntunneling them.\r\n2. Foreign Agent (FA):\r\n○ Located in the foreign network.\r\n○ Provides a care-of address for the mobile node.\r\n○ Decapsulates tunneled packets from the home agent and delivers\r\nthem to the mobile node.\r\nThree Phases of Mobile IP\r\n1. Agent Discovery:\r\n○ Mobile nodes listen for advertisements from home agents and foreign\r\nagents to determine their current network status.\r\n38\r\n', '2024-11-15 21:48:24', 38, '2024-11-15 21:48:24', 3),
(17, 'M.SHAKIL PATEL\r\n3.9.2 IPv6 Address Space&Address Space Allocation\r\nIPv6 Address Space:\r\n● IPv6 provides 2^128 addresses, which is approximately 340 undecillion (3.4 x\r\n10^38) IP addresses. This enormous address space solves the exhaustion\r\nissue faced in IPv4, where there are only about 4.3 billion addresses.\r\nAddress Space Allocation: IPv6 addresses are divided into several blocks for\r\ndifferent uses, similar to IPv4, but with much larger ranges:\r\n1. Unicast Addresses: Used for one-to-one communication, where a single\r\ndevice is addressed directly.\r\n○ Types: Global Unicast, Link-local, and Unique Local addresses.\r\n2. Multicast Addresses: Used for one-to-many communication, allowing a\r\npacket to be delivered to multiple interfaces.\r\n3. Anycast Addresses: Assigned to multiple interfaces, where a packet is\r\ndelivered to the nearest interface.\r\n4. Reserved and Special Use Addresses: Includes addresses reserved for specific\r\npurposes, like ::1 for loopback.\r\nIPv6 Prefixes:\r\n● Global Unicast: Globally routable addresses, typically assigned a /48 prefix.\r\n● Link-Local: Used within a single link, not routable globally, typically with the\r\nprefix FE80::/10.\r\n3.9.3 Auto-Configuration and Renumbering\r\nIPv6 introduces two important features to simplify network management: Stateless\r\nAddress Auto-Configuration (SLAAC) and Renumbering.\r\nAuto-Configuration:\r\n16\r\n', '2024-11-15 21:48:23', 16, '2024-11-15 21:48:23', 3),
(18, 'M.SHAKIL PATEL\r\nSteps to calculate the checksum:\r\n1. Break the IP header into 16-bit segments.\r\n2. Add the segments together.\r\n3. Take the one\'s complement of the sum and place it in the checksum field.\r\nIf the datagram is fragmented, each fragment will have its own checksum.\r\n3.5.5 IP Package\r\nThe IP package is the complete process of encapsulating data into an IP datagram\r\nand preparing it for transmission over a network. This involves several steps,\r\nincluding creating the IP header, calculating the checksum, fragmenting the packet if\r\nnecessary, and passing it to the appropriate lower-layer protocol (usually Ethernet or\r\nanother link layer protocol).\r\nSteps in the IP packaging process:\r\n1. Data encapsulation: Data from higher-level protocols (e.g., TCP or UDP) is\r\nencapsulated in an IP datagram.\r\n2. Header creation: The IP header is created, including source and destination\r\naddresses, TTL, and other fields.\r\n3. Checksumcalculation: The checksum for the IP header is computed.\r\n4. Fragmentation (if needed): If the datagram is too large for the network\'s MTU,\r\nit is fragmented.\r\n5. Forwarding: The datagram is passed to the link layer for transmission over the\r\nphysical network.\r\n6. Routing: If the destination is on another network, the datagram is forwarded\r\nby routers along the path to the destination.\r\nThe process continues until the datagram reaches its destination, where the IP\r\npackage is stripped, and the data is passed to the higher-layer protocol for further\r\nprocessing.\r\n10\r\n', '2024-11-15 21:48:23', 10, '2024-11-15 21:48:23', 3),
(19, 'M.SHAKIL PATEL\r\nThe home address is used to identify the mobile device, while the care-of address is\r\nused to route the datagrams when the device is away from its home network.\r\n3.8.2 Agents inMobile IP\r\nTwo key agents are involved in the operation of Mobile IP:\r\n1. HomeAgent (HA): A router on the home network that keeps track of the\r\nmobile device’s current location (care-of address). It forwards packets to the\r\nmobile device when it is away from home.\r\n2. Foreign Agent (FA): A router on the foreign network that assigns care-of\r\naddresses to visiting mobile devices and helps forward their traffic.\r\nRoles of the Agents:\r\n● The HomeAgent stores the care-of address and acts as a relay, intercepting\r\npackets meant for the mobile device and tunneling them to its current\r\nlocation.\r\n● The Foreign Agent helps the mobile device register its care-of address and\r\nfacilitates communication between the home agent and the mobile device.\r\n3.8.3 Three Phases of Mobile IP Operation\r\nMobile IP operates in three distinct phases:\r\n1. Agent Discovery:\r\n○ Mobile devices detect when they are on a foreign network by listening\r\nfor advertisements from foreign agents. These agents broadcast their\r\npresence and offer services.\r\n13\r\n', '2024-11-15 21:48:23', 13, '2024-11-15 21:48:23', 3),
(20, 'M.SHAKIL PATEL\r\nIn networks without subnetting, all devices share a single IP network address range.\r\nThe forwarding process is straightforward because there\'s only one large network,\r\nbut this approach can lead to inefficiencies, especially in larger networks.\r\n● Figure: (Single large network without subnets)\r\nRouter A Router B\r\n+--------------+ +--------------+\r\n| 192.168.0.1| --------------| 192.168.0.254|\r\n+--------------+ +--------------+\r\n| |\r\n| |\r\n[Single large network: 192.168.0.0/24]\r\n| |\r\n+-------------------+ +-------------------+\r\n| Device 1 (0.2) | | Device 2 (0.3) |\r\n+-------------------+ +-------------------+\r\n● Key Characteristics:\r\n1. One large network (e.g., 192.168.0.0/24).\r\n2. Devices belong to a single broadcast domain.\r\n3. Simple forwarding: Packets are easily forwarded between devices\r\nbecause they all belong to the same network.\r\n4. Inefficiencies: As the network grows, routing tables can become large,\r\nand broadcasts will flood the entire network.\r\n● Forwarding Process:\r\n1. Device 1 (192.168.0.2) wants to communicate with Device 2\r\n(192.168.0.3).\r\n2. Router A forwards packets directly to the destination since all devices\r\nare part of the same network.\r\n2. Forwardingwith Subnetting\r\nWhen a network is subnetted, it is divided into smaller, logical sub-networks, each\r\nwith its own address range. Subnetting optimizes routing by breaking the larger\r\n29\r\n', '2024-11-15 21:48:24', 29, '2024-11-15 21:48:24', 3),
(21, 'M.SHAKIL PATEL\r\nThe header contains information like the source and destination IP addresses,\r\nTTL (Time to Live), protocol (TCP, UDP), etc.\r\n● Routing: Routers are devices that direct packets between different networks.\r\nThey use routing tables and algorithms to determine the best path for data to\r\ntravel from the source to the destination.\r\n○ Static Routing: Predefined routes set by network administrators.\r\n○ Dynamic Routing: Routers use algorithms and protocols like OSPF\r\n(Open Shortest Path First) and BGP (Border Gateway Protocol) to\r\ndynamically calculate the best path based on current network\r\nconditions.\r\n5\r\n', '2024-11-15 21:48:22', 5, '2024-11-15 21:48:22', 3),
(22, 'M.SHAKIL PATEL\r\n○ The home agent (HA) on the mobile node’s home network knows the\r\ncare-of address and encapsulates and forwards any packets destined\r\nfor the mobile node.\r\n○ The foreign agent (FA) on the foreign network assists in forwarding\r\nthese encapsulated packets to the mobile node.\r\nThe communication process can be broken into three phases: Agent Discovery,\r\nRegistration, and Tunneling.\r\nPhases of Mobile IP\r\n1. Agent Discovery:\r\n○ The mobile node uses agent discovery to determine whether it is on its\r\nhome network or a foreign network.\r\n○ The homeagent and foreign agent broadcast their availability via\r\nspecial advertisement messages.\r\n○ The mobile node listens for these advertisements to identify which\r\nnetwork it is currently connected to.\r\n2. Registration:\r\n○ If the mobile node detects that it has moved to a foreign network, it\r\nregisters its current care-of address with the home agent via the\r\nforeign agent.\r\n○ The home agent acknowledges the registration and maintains a\r\nmapping between the mobile node’s permanent IP address and the\r\ntemporary care-of address.\r\n3. Tunneling:\r\n○ Once the mobile node is registered on the foreign network, the home\r\nagent forwards any incoming packets for the mobile node through a\r\ntunnel to the care-of address.\r\n○ The foreign agent (or the mobile node itself, if it has a direct care-of\r\naddress) decapsulates the packets and delivers them to the mobile\r\nnode.\r\n37\r\n', '2024-11-15 21:48:24', 37, '2024-11-15 21:48:24', 3),
(23, 'M.SHAKIL PATEL\r\nMobile IP\r\nMobile IP is a communication protocol that allows mobile devices to move across\r\ndifferent networks while maintaining a permanent IP address. It enables seamless,\r\nuninterrupted communication as the device moves between different networks, such\r\nas from Wi-Fi to cellular or across geographical regions, while keeping its home IP\r\naddress. This protocol is particularly important for mobile computing, where devices\r\nneed to maintain continuous connectivity without needing to change their IP address\r\nwhen moving.\r\nKey Concepts of Mobile IP:\r\n● HomeAddress: The permanent IP address assigned to the mobile node\r\n(device) on its home network. This address remains unchanged regardless of\r\nwhere the mobile node connects to the internet.\r\n● Care-of Address (CoA): A temporary IP address assigned to the mobile node\r\nwhen it moves to a foreign network. The care-of address changes as the\r\nmobile node moves between networks.\r\n● HomeAgent (HA): A router in the mobile node\'s home network that tracks its\r\ncurrent location (care-of address) and forwards packets to it when it is away\r\nfrom home.\r\n● Foreign Agent (FA): A router in the foreign network that provides routing\r\nservices to the mobile node when it is away from its home network, helping\r\ndeliver data between the mobile node and its home agent.\r\nHowMobile IPWorks:\r\n1. HomeNetwork: When a mobile device (node) is connected to its home\r\nnetwork, it functions like any other device, using its permanent home IP\r\naddress to communicate.\r\n2. Movement to Foreign Network: When the mobile node moves to a foreign\r\nnetwork (away from home), it obtains a temporary care-of address (CoA) in\r\nthe foreign network.\r\n3. Communication:\r\n36\r\n', '2024-11-15 21:48:24', 36, '2024-11-15 21:48:24', 3),
(24, 'M.SHAKIL PATEL\r\n● Network Diagnostics: Tools like ping (to check if a host is online) and\r\ntraceroute (to see the route a packet takes).\r\n● Error Reporting: A router might send an ICMP \"destination unreachable\"\r\nmessage if it can\'t forward a packet.\r\nIGMP (Internet GroupManagement Protocol)\r\nThe Internet GroupManagement Protocol (IGMP) is a communication protocol used\r\nby hosts and routers to manage the membership of IP multicast groups. It operates\r\nat the network layer and is essential for managing group communication, especially\r\nin broadcasting applications such as video streaming.\r\nKey Features of IGMP:\r\n1. Multicast GroupMembership: IGMP allows a host to report its multicast group\r\nmemberships to routers so that it can receive traffic from those groups.\r\n2. EfficientMulticast Delivery: IGMP ensures that multicast traffic is only sent to\r\nnetworks where group members exist, minimizing unnecessary data\r\ntransmission.\r\n3. Used inMulticast Applications: It is commonly used in streaming video, online\r\ngaming, and other real-time, group-based communication scenarios.\r\nIGMP Versions:\r\n● IGMPv1: The original version, hosts report group membership when they want\r\nto join a multicast group.\r\n● IGMPv2: Adds the ability to leave a group and includes a \"Leave Group\"\r\nmessage.\r\n● IGMPv3: Introduces source filtering, allowing hosts to specify which sources\r\nthey want to receive multicast traffic from (useful for advanced applications\r\nlike IPTV).\r\nIGMPMessage Types:\r\n33\r\n', '2024-11-15 21:48:24', 33, '2024-11-15 21:48:24', 3),
(25, 'M.SHAKIL PATEL\r\nError Control None Yes, ensures error-free\r\ntransmission through\r\nacknowledgment,\r\nretransmission, and\r\nchecksums\r\nBasic error\r\nreporting via\r\nprotocols like\r\nICMP; not\r\nresponsible for\r\nretransmission\r\nPacket\r\nFragmentation\r\nNone None Handles\r\nfragmentation of\r\npackets if they\r\nexceed the\r\nMaximum\r\nTransmission Unit\r\n(MTU)\r\nData Flow Directs bits over\r\nthe medium (e.g.,\r\ntwisted pair\r\ncables, fiber\r\noptics)\r\nOrganizes these bits into\r\nframes and sends them\r\nover a specific link\r\nRoutes packets\r\nfrom source to\r\ndestination,\r\npotentially across\r\nmultiple\r\nlinks/networks\r\nMultiplexing Uses techniques\r\nlike Time Division\r\nMultiplexing\r\n(TDM) and\r\nFrequency\r\nDivision\r\nMultiplexing\r\n(FDM)\r\nMay use multiplexing\r\nfor efficient data\r\ntransmission over a link\r\nSupports\r\nmultiplexing in\r\nterms of sharing\r\nnetwork paths\r\namong multiple\r\ndata flows\r\nExamples of\r\nDevices\r\nHubs, Repeaters,\r\nModems, Cables\r\nNetwork switches,\r\nBridges\r\nRouters, Layer 3\r\nSwitches\r\nKey Differences Explained:\r\n1. Physical Layer (Layer 1):\r\n○ Deals with raw transmission of data (bits) over physical media like\r\ncables or radio signals.\r\n27\r\n', '2024-11-15 21:48:24', 27, '2024-11-15 21:48:24', 3),
(26, 'M.SHAKIL PATEL\r\nUNIT : 3 Network Layer & Protocols\r\n3.1 IP Addressing\r\nIP (Internet Protocol) addressing is the method used to label devices (hosts)\r\nconnected to a network. Each device in a network is assigned a unique IP address,\r\nwhich is used to identify and communicate with it across the internet. IP addresses\r\nare essential in ensuring data packets reach the correct destination.\r\n3.1.1 IP Classful Addressing\r\nClassful IP addressing was the original method of dividing IP addresses into\r\npredefined blocks or classes. These classes (A, B, C, D, and E) were based on the\r\nhigh-order bits of the first octet and determined how many networks and hosts\r\ncould exist.\r\n● Class A:\r\n○ Range: 0.0.0.0 to 127.255.255.255\r\n○ First bit: 0\r\n○ Network: 8 bits | Hosts: 24 bits\r\n○ Supports 128 networks with 16,777,214 hosts per network.\r\n● Class B:\r\n○ Range: 128.0.0.0 to 191.255.255.255\r\n○ First two bits: 10\r\n○ Network: 16 bits | Hosts: 16 bits\r\n○ Supports 16,384 networks with 65,534 hosts per network.\r\n● Class C:\r\n○ Range: 192.0.0.0 to 223.255.255.255\r\n○ First three bits: 110\r\n○ Network: 24 bits | Hosts: 8 bits\r\n○ Supports 2,097,152 networks with 254 hosts per network.\r\n1\r\n', '2024-11-15 21:48:21', 1, '2024-11-15 21:48:21', 3),
(27, 'M.SHAKIL PATEL\r\nHow IGMPWorks:\r\n● IGMP allows devices to join or leave multicast groups.\r\n● Routers use IGMP to track which hosts are members of specific multicast\r\ngroups and forward multicast traffic accordingly.\r\nIGMP Versions:\r\n1. IGMPv1: The simplest version, where hosts can only join groups.\r\n2. IGMPv2: Adds the ability for hosts to explicitly leave groups.\r\n3. IGMPv3: Supports source-specific multicast, where hosts can request traffic\r\nfrom specific sources only.\r\nIGMP Roles:\r\n● Host: A device that wants to receive multicast traffic.\r\n● Router: A device that listens for IGMP reports from hosts to manage multicast\r\ngroup traffic.\r\n3.8Mobile IP\r\nMobile IP is a protocol that allows mobile devices to move across different networks\r\nwhile maintaining a permanent IP address. This ensures that ongoing sessions, such\r\nas video calls or file transfers, are not interrupted as the mobile device changes\r\nlocations.\r\n3.8.1 Addressing inMobile IP\r\nIn Mobile IP, two types of addresses are used to manage the movement of the\r\nmobile device:\r\n1. HomeAddress: A permanent IP address assigned to the mobile device in its\r\nhome network. It remains the same regardless of the device’s current location.\r\n2. Care-of Address (CoA): A temporary IP address assigned to the mobile\r\ndevice when it is in a foreign network. This address changes as the device\r\nmoves from one network to another.\r\n12\r\n', '2024-11-15 21:48:23', 12, '2024-11-15 21:48:23', 3),
(28, 'M.SHAKIL PATEL\r\n○ Concerned with hardware elements, like voltage levels, timing, and bit\r\nrate.\r\n2. Data Link Layer (Layer 2):\r\n○ Adds structure to the raw bits from the physical layer by grouping them\r\ninto frames.\r\n○ Responsible for error detection (e.g., via CRC) and flow control.\r\n○ UsesMACaddresses to handle node-to-node communication (within\r\nthe same network or link).\r\n3. Network Layer (Layer 3):\r\n○ Handles end-to-end communication across networks.\r\n○ Responsible for routing data packets using logical IP addresses, and\r\ndetermining the best path for data to travel.\r\n○ Manages packet fragmentation and reassembly if the size exceeds the\r\nallowed MTU for a network link.\r\nPractical Analogy:\r\n● The Physical Layer is like the road where vehicles (data) travel.\r\n● The Data Link Layer is like traffic signals and rules that manage the vehicles’\r\nmovement within a particular region or city.\r\n● The Network Layer is like the GPS system that decides which route (path) the\r\nvehicles should take to reach their destination across different cities or\r\nregions.\r\nForwardingwith Subnetting vsWithout Subnetting\r\nForwarding is the process of transferring packets from one network segment to\r\nanother, but its efficiency and management can be greatly affected by whether\r\nsubnetting is used or not. Subnetting divides a larger network into smaller, more\r\nmanageable sub-networks (subnets), while forwarding without subnetting uses a\r\nsingle large network without dividing it.\r\n1. ForwardingWithout Subnetting\r\n28\r\n', '2024-11-15 21:48:24', 28, '2024-11-15 21:48:24', 3),
(29, 'M.SHAKIL PATEL\r\n● Function: RARP is used primarily in diskless workstations that do not have the\r\ncapability to store their own IP address. Upon booting, they use RARP to\r\ndiscover their IP address by sending a request containing their MAC address.\r\n● Process: The RARP request is broadcast to the network, and a RARP server\r\nresponds with the corresponding IP address mapped to that MAC address.\r\n● Limitation: RARP has been largely replaced by protocols like BOOTP and DHCP,\r\nwhich offer more functionality, including dynamic IP address assignment and\r\nadditional configuration parameters.\r\n3.5 Internet Protocol (IP)\r\nThe Internet Protocol (IP) is a core protocol in the Internet Protocol Suite used for\r\nrelaying datagrams across networks. IP is a connectionless, best-effort protocol,\r\nmeaning it does not guarantee delivery or error correction. IP provides addressing\r\nand routing capabilities, enabling data packets to traverse multiple networks.\r\n3.5.1 Datagram\r\nAn IP datagram is a fundamental unit of data in the IP protocol. It encapsulates data\r\nto be sent from a source to a destination across networks. A datagram is analogous\r\nto an envelope carrying a letter, where the IP header is the envelope, and the data is\r\nthe letter.\r\nIP DatagramStructure:\r\n● Header: Contains information such as source and destination IP addresses,\r\nlength, protocol version, and flags.\r\n● Payload: The actual data being transmitted.\r\nKey Fields in an IP Datagram:\r\n1. Version: Specifies the IP version (IPv4 or IPv6).\r\n2. Header Length: The length of the header (usually 20 bytes for IPv4).\r\n7\r\n', '2024-11-15 21:48:23', 7, '2024-11-15 21:48:23', 3),
(30, 'M.SHAKIL PATEL\r\n● Class D:\r\n○ Range: 224.0.0.0 to 239.255.255.255\r\n○ First four bits: 1110\r\n○ Used formulticasting, not for host/network addressing.\r\n● Class E:\r\n○ Range: 240.0.0.0 to 255.255.255.255\r\n○ First four bits: 1111\r\n○ Reserved for experimental purposes.\r\n3.1.1.1 Subnetting & Supernetting\r\n● Subnetting: Subnetting divides a larger network into smaller sub-networks\r\n(subnets). This helps in better IP management, increases security, and\r\nreduces network traffic.\r\nExample: A Class B network with IP range 172.16.0.0 can be divided into\r\nmultiple subnets by borrowing bits from the host portion to extend the network\r\nportion.\r\n○ Default subnet mask: 255.255.0.0\r\n○ By using 255.255.255.0, we create subnets where the third octet is\r\nused for sub-network identification, reducing the number of hosts in\r\neach subnet.\r\n● Supernetting: Supernetting combines multiple smaller networks into a larger\r\nnetwork. This technique is the opposite of subnetting and is used to reduce the\r\nsize of routing tables by aggregating several routes into one.\r\n3.1.2 IP Classless Addressing\r\nIn classless addressing, networks can be divided into arbitrary sizes regardless of\r\nthe predefined classes (A, B, or C). The key difference is that Classless Inter-Domain\r\nRouting (CIDR) is used, where an IP address is followed by a prefix (slash notation)\r\nthat denotes how many bits are used for the network portion.\r\n2\r\n', '2024-11-15 21:48:22', 2, '2024-11-15 21:48:22', 3),
(31, 'M.SHAKIL PATEL\r\n○ When a mobile device moves to a foreign network, it receives these\r\nadvertisements and determines its care-of address.\r\n2. Registration:\r\n○ The mobile device registers its care-of address with its home agent.\r\nThis informs the home agent that the mobile device is away from its\r\nhome network and provides the address where it can be reached.\r\n○ The registration process includes authentication to ensure security.\r\n○ Once registered, the home agent will forward all datagrams destined\r\nfor the mobile device to the care-of address via tunneling.\r\n3. Tunneling:\r\n○ When the home agent receives a packet destined for the mobile\r\ndevice’s home address, it encapsulates the packet inside a new packet\r\nand forwards it to the care-of address. This process is called tunneling.\r\n○ The foreign agent receives the tunneled packet, decapsulates it, and\r\nforwards it to the mobile device.\r\n3.8.4 Inefficiency inMobile IP\r\nWhile Mobile IP solves the problem of maintaining ongoing communication for\r\nmobile devices, it introduces several inefficiencies:\r\n1. Triangular Routing: In some cases, packets destined for the mobile device\r\nmust travel through the home agent, even when the sender and receiver are\r\nphysically close. This creates inefficient routing paths.\r\n○ Example: A device in one city may communicate with a mobile device\r\nin the same city, but the data must first travel to the home agent in\r\nanother city, creating delays.\r\n2. Tunneling Overhead: Mobile IP relies on tunneling to forward packets from the\r\nhome agent to the mobile device. This adds overhead in the form of additional\r\nheaders, reducing the effective payload size.\r\n3. Security Concerns: Mobile IP requires careful management of security,\r\nespecially in the registration phase. Attackers could potentially redirect traffic\r\n14\r\n', '2024-11-15 21:48:23', 14, '2024-11-15 21:48:23', 3),
(32, 'M.SHAKIL PATEL\r\nARP (Address Resolution Protocol) and PARP (Proxy Address\r\nResolution Protocol)\r\nARP (Address Resolution Protocol)\r\nARP is a protocol used to map an IP address to a physical machine address (MAC\r\naddress) in a local area network (LAN). When a device wants to communicate with\r\nanother device on the same network, it needs the MAC address associated with the\r\nIP address of the destination device.\r\nARPWorkflow:\r\n1. ARP Request: When a host wants to communicate with another device and\r\nknows only the IP address, it sends an ARP request in the form of a broadcast\r\nasking, \"Who has this IP address? Please send me your MAC address.\"\r\n2. ARP Reply: The device with the matching IP address responds with an ARP\r\nreply, containing its MAC address.\r\nOnce the MAC address is obtained, the sender can encapsulate the data in a frame\r\nand forward it to the appropriate host in the network.\r\n● ARPCache: Hosts store mappings of IP addresses to MAC addresses in a table\r\ncalled the ARP cache, allowing them to avoid sending ARP requests\r\nrepeatedly.\r\n● Gratuitous ARP: A device can send an ARP response without receiving an ARP\r\nrequest, typically to update the ARP tables of other devices after its IP or MAC\r\naddress changes.\r\nRARP (Reverse Address Resolution Protocol)\r\nRARP is a protocol used by a computer to request its IP address from a gateway\r\nserver using itsMACaddress. It is essentially the reverse of the ARP (Address\r\nResolution Protocol), which resolves an IP address to a MAC address.\r\n6\r\n', '2024-11-15 21:48:23', 6, '2024-11-15 21:48:23', 3),
(33, 'M.SHAKIL PATEL\r\nTransition\r\nStrategy\r\nDual Stack, NAT64,\r\nand others\r\nDesigned to coexist with IPv4 during the\r\ntransition phase\r\nSupported by Older networking\r\nhardware and software\r\nModern networking hardware and\r\nsoftware, gradually becoming more\r\nwidely supported\r\nKey Differences Explained:\r\n1. Address Space:\r\n○ IPv4 provides about 4.3 billion addresses, which has become insufficient\r\ndue to the growth of the internet. IPv6 offers an almost unlimited\r\nnumber of addresses.\r\n2. Address Format:\r\n○ IPv4 uses a 32-bit address format, which is represented in decimal\r\nnotation (e.g., 192.168.1.1). IPv6 uses a 128-bit format, represented in\r\nhexadecimal, which accommodates more addresses (e.g.,\r\n2001:0db8:85a3::8a2e:0370:7334).\r\n3. Header Complexity:\r\n○ IPv4 headers are more complex, with optional fields and fragmentation\r\nhandled by both routers and the sender. IPv6 simplifies the header by\r\nremoving fields like the checksum and options and pushes\r\nfragmentation to the sender.\r\n4. Address Configuration:\r\n○ In IPv4, devices need to either manually configure their IP addresses or\r\nuse DHCP. In IPv6, devices can configure themselves automatically\r\n(using SLAAC), reducing administrative overhead.\r\n5. Security:\r\n○ IPv6 has security features built-in, with IPSec being a mandatory part of\r\nthe protocol, whereas IPv4 relies on additional layers for security.\r\n6. NAT:\r\n○ IPv4 heavily relies on NAT (Network Address Translation) to deal with the\r\naddress shortage by allowing multiple devices to share a single public\r\n24\r\n', '2024-11-15 21:48:23', 24, '2024-11-15 21:48:23', 3);
INSERT INTO `notes_text` (`id`, `content`, `created_at`, `page_no`, `updated_at`, `notes_id`) VALUES
(34, 'M.SHAKIL PATEL\r\n○ Agents periodically broadcast these advertisements to announce their\r\npresence.\r\n2. Registration:\r\n○ The mobile node registers its current care-of address with its home\r\nagent, either directly or via the foreign agent.\r\n○ This allows the home agent to update its records and tunnel packets to\r\nthe mobile node’s new location.\r\n3. Tunneling:\r\n○ The home agent intercepts packets meant for the mobile node’s home\r\naddress and tunnels them to the care-of address.\r\n○ When the mobile node sends packets back, they are sent directly to the\r\ndestination without needing to go through the home agent.\r\nInefficiencies inMobile IP\r\nWhile Mobile IP provides a way for devices to roam seamlessly across networks, it\r\ndoes have some inefficiencies:\r\n1. Triangular Routing:\r\n○ In Mobile IP, packets destined for the mobile node are first sent to the\r\nhome agent, even when the mobile node is far away, leading to longer\r\npaths and delays.\r\n○ This issue is called triangular routing, where packets take an indirect\r\nroute through the home agent instead of being sent directly to the\r\nmobile node.\r\n2. Encapsulation Overhead:\r\n○ Mobile IP uses encapsulation to tunnel packets between the home\r\nagent and the care-of address. This adds extra overhead in the packet,\r\nreducing the available bandwidth.\r\n3. Security Concerns:\r\n○ Mobile IP requires secure registration and tunneling processes to\r\nprevent malicious nodes from intercepting or misdirecting traffic.\r\n39\r\n', '2024-11-15 21:48:24', 39, '2024-11-15 21:48:24', 3),
(35, 'M.SHAKIL PATEL\r\nIP. IPv6, with its vast address space, eliminates the need for NAT,\r\nallowing direct end-to-end communication.\r\n7. Transition:\r\n○ Due to the widespread use of IPv4, several transition strategies (like\r\nDual Stack, where both IPv4 and IPv6 operate simultaneously, and\r\nNAT64) have been developed to allow gradual adoption of IPv6.\r\nIPv6 is designed to replace IPv4 in the long term by addressing its limitations,\r\nespecially the exhaustion of available IP addresses.\r\nPhysical Layer, Data Link Layer, and Network Layer in the OSI model:\r\nFeature Physical Layer Data Link Layer Network Layer\r\nLayer Number in\r\nOSI Model\r\nLayer 1 Layer 2 Layer 3\r\nFunction Responsible for\r\nthe transmission\r\nof raw bit streams\r\nover a physical\r\nmedium (e.g.,\r\ncables, radio\r\nwaves)\r\nProvides reliable data\r\ntransfer between two\r\ndirectly connected nodes\r\nResponsible for\r\npacket forwarding,\r\nincluding routing\r\nthrough\r\nintermediate\r\nrouters\r\nData Unit Bits Frames Packets\r\nMain\r\nResponsibility\r\nTransmission and\r\nreception of raw\r\nbinary data (0s\r\nand 1s)\r\nFraming, addressing,\r\nand error\r\ndetection/correction\r\nLogical addressing\r\nand routing of\r\npackets across\r\nnetworks\r\n25\r\n', '2024-11-15 21:48:23', 25, '2024-11-15 21:48:23', 3),
(36, 'M.SHAKIL PATEL\r\n3.5.3 IP Options\r\nThe IP header can contain optional fields that provide additional information about\r\nthe packet or request specific handling by routers.\r\nSome common IP options:\r\n● Record Route: This option stores the route that the datagram takes across the\r\nnetwork.\r\n● Timestamp: Stores the time the packet was processed by each router.\r\n● Strict Source Route: Specifies the exact route the packet must follow through\r\nthe network.\r\n● Loose Source Route: Allows the packet to go through a set of specified routers\r\nbut doesn’t dictate the exact path.\r\nThese options add overhead to the IP header, so they are used sparingly in most\r\nnetworks.\r\n3.5.4 Checksum\r\nThe IP checksum is used for error-checking the IP header to ensure its integrity. It\r\nhelps detect corruption during transmission.\r\nHow the checksumworks:\r\n● The checksum is computed by taking the one\'s complement sum of all 16-bit\r\nwords in the IP header.\r\n● If any bit errors occur during transmission, the checksum value will not match,\r\nand the datagram will be discarded by the receiving router or device.\r\n● Note: The checksum is only applied to the IP header, not the payload (data),\r\nbecause other protocols like TCP or UDP have their own error-checking\r\nmechanisms for the data portion.\r\n9\r\n', '2024-11-15 21:48:23', 9, '2024-11-15 21:48:23', 3),
(37, 'M.SHAKIL PATEL\r\nThe transition from IPv4 to IPv6 is expected to be gradual, with both protocols\r\ncoexisting for many years.\r\n3.9.5 IPv6 Protocol\r\nThe IPv6 protocol is designed to improve upon IPv4 in terms of efficiency, flexibility,\r\nand security. IPv6 introduces a streamlined header format, optional extension\r\nheaders, and better support for modern networking needs, such as mobility and\r\nreal-time communications.\r\n3.9.5.1 Packet Format\r\nThe IPv6 packet header is much simpler than IPv4, which helps with processing\r\nspeed and efficiency.\r\nKey Fields in the IPv6 Header:\r\n1. Version (4 bits): Specifies the IP version (set to 6 for IPv6).\r\n2. Traffic Class (8 bits): Similar to the Type of Service field in IPv4, used for QoS\r\n(Quality of Service) and prioritizing packets.\r\n3. Flow Label (20 bits): Used to label sequences of packets for special handling,\r\nsuch as real-time streams.\r\n4. Payload Length (16 bits): Indicates the size of the payload (excluding the\r\nheader).\r\n5. Next Header (8 bits): Points to the next header in the packet, such as a\r\ntransport layer protocol (TCP, UDP) or an extension header.\r\n6. Hop Limit (8 bits): Replaces the TTL (Time to Live) field in IPv4. This limits the\r\nnumber of hops a packet can take before being discarded.\r\n7. Source Address (128 bits): The IPv6 address of the sender.\r\n8. Destination Address (128 bits): The IPv6 address of the receiver.\r\n18\r\n', '2024-11-15 21:48:23', 18, '2024-11-15 21:48:23', 3),
(38, 'M.SHAKIL PATEL\r\nImportant Topics\r\nClassful and Classless IP addressing in tabular format:\r\nFeature Classful Addressing Classless Addressing (CIDR)\r\nAddress Format Divided into predefined classes\r\n(A, B, C, D, E)\r\nUses a prefix length (e.g., /24)\r\nto define network size\r\nNetwork/Host\r\nPortion\r\nFixed boundary between\r\nnetwork and host portions\r\nbased on class (e.g., Class A =\r\n/8, Class B = /16)\r\nVariable boundary, network and\r\nhost parts are flexible\r\nIP Address\r\nAllocation\r\nAllocates addresses based on\r\nclass, which may waste\r\naddress space\r\nAllows more efficient allocation\r\nof IP addresses by customizing\r\nnetwork sizes\r\nSubnetting Requires manual subnetting\r\nand introduces complexity\r\nSubnetting is more flexible and\r\neasier due to the use of\r\nvariable-length subnet masks\r\n(VLSM)\r\nAddress Range Limited to class-based ranges,\r\nwhich are predefined\r\nNo fixed class ranges,\r\naddresses can be assigned\r\nmore dynamically\r\nWaste of IP\r\nAddresses\r\nSignificant wastage due to\r\npredefined large blocks\r\nMinimal wastage as blocks are\r\nallocated based on exact needs\r\nRouting Classful routing protocols (e.g.,\r\nRIPv1, IGRP) do not support\r\nVLSM, and routers assume the\r\nclass of an address\r\nClassless routing protocols (e.g.,\r\nOSPF, BGP, EIGRP, RIPv2)\r\nsupport VLSM and aggregate\r\nroutes efficiently\r\n20\r\n', '2024-11-15 21:48:23', 20, '2024-11-15 21:48:23', 3),
(39, 'M.SHAKIL PATEL\r\nNetwork\r\nStructure\r\nA single large network, all\r\ndevices on one subnet.\r\nDivides the network into smaller\r\nsubnets for better management.\r\nBroadcast\r\nTraffic\r\nHigh: All devices receive\r\nbroadcasts from all others.\r\nReduced: Broadcasts are\r\ncontained within each subnet.\r\nForwarding\r\nEfficiency\r\nLess efficient in large\r\nnetworks, as all traffic flows\r\nthrough one large network.\r\nMore efficient, as routers only\r\nforward traffic between subnets.\r\nRouting\r\nComplexity\r\nSimple routing with minimal\r\ntables, but scalability issues as\r\nthe network grows.\r\nRequires more complex routing\r\nwith separate tables for each\r\nsubnet.\r\nNetwork\r\nManagement\r\nDifficult to manage large\r\nnetworks as a single block.\r\nEasier to manage, as each subnet\r\ncan be allocated and controlled\r\nindependently.\r\nScalability Poor scalability for larger\r\nnetworks.\r\nHighly scalable, as new subnets\r\ncan be easily added.\r\nSecurity Less secure, as all devices\r\nshare the same network.\r\nMore secure, as subnets can be\r\nisolated and firewalled.\r\nConclusion\r\n● Forwardingwithout subnetting is simpler but less efficient and scalable,\r\nparticularly for larger networks.\r\n● Forwardingwith subnetting divides the network into manageable parts,\r\nmaking it easier to optimize routing, control broadcast domains, and enhance\r\nsecurity and scalability.\r\nICMP (Internet Control Message Protocol)\r\n31\r\n', '2024-11-15 21:48:24', 31, '2024-11-15 21:48:24', 3),
(40, 'M.SHAKIL PATEL\r\nMessage Types Echo Request/Reply, Destination\r\nUnreachable, Time Exceeded,\r\nRedirect, etc.\r\nMembership Query,\r\nMembership Report, Leave\r\nGroup\r\nLayer Network Layer Network Layer\r\nApplications Network troubleshooting (ping,\r\ntraceroute), error handling\r\nIPTV, live streaming, online\r\ngaming\r\nCommunication\r\nType\r\nUnicast (one-to-one\r\ncommunication)\r\nMulticast (one-to-many\r\ncommunication)\r\nVersions No versions, but part of the IP\r\nsuite\r\nIGMPv1, IGMPv2, IGMPv3\r\nData Transfer Does not carry actual data Manages membership for\r\nmulticast data\r\nConclusion\r\n● ICMP is vital for error reporting and diagnostics in IP networks, ensuring\r\nnetwork problems are detected and reported quickly.\r\n● IGMP is essential for multicast communication, enabling efficient transmission\r\nof data to multiple recipients at the same time.\r\nBoth protocols operate at the network layer, but their purposes and applications are\r\ndifferent—ICMP is used for diagnostic purposes, while IGMP is used for managing\r\nmulticast group membership.\r\n35\r\n', '2024-11-15 21:48:24', 35, '2024-11-15 21:48:24', 3),
(41, '102  (JAVA) 103  (ISA)\r\n104 \r\n(ACN)\r\n105\r\nPractical  1\r\n106\r\nPractical  2\r\nTotal\r\n13 20 15 16 18 105\r\n14 24 16 17 17 107\r\n12 17 14 17 18 92\r\n23 22 21 22 18 131\r\n21 24 20 25 22 139\r\n18 19 22 18 22 120\r\n25 27 28 24 24 153\r\n14 24 13 28 19 124\r\n16 23 19 22 23 125\r\n13 17 20 19 22 111\r\n16 24 24 19 22 124\r\n12 22 12 6 2 68\r\n16 22 18 21 18 117\r\n21 22 20 19 24 127\r\n12 8 7 9 8 56\r\n19 25 26 23 17 134\r\n26 29 25 25 22 150\r\n26 27 26 28 18 146\r\n22 27 27 26 26 151\r\n28 29 27 25 29 163\r\n19 24 23 26 24 134\r\n15 20 17 21 21 114\r\n16 21 19 19 19 116\r\n17 27 27 28 28 153\r\n21 24 14 27 18 129\r\n14 15 19 19 17 98\r\n20 21 28 23 18 131\r\n14 18 14 22 17 100\r\n1 12 6 1 1 28\r\n12 22 12 16 16 94\r\n21 27 24 23 19 139\r\n19 20 18 20 22 116\r\n25 27 24 25 22 150\r\n15 18 17 19 21 108\r\n12 24 21 22 17 116\r\n21 21 18 19 27 125\r\n14 24 22 28 27 141\r\n16 25 20 20 19 120\r\nM.Sc.  ICT  1st  Sem  Internal  Theory  Examination  2024  (out  of  30)\r\n', '2024-12-18 19:15:17', 3, '2024-12-18 19:15:17', 12),
(42, '46 1033 PARMAR  DEEP  JITENDRAKUMAR 16\r\n47 1066 PARMAR  MAHEK  ABHINAV 21\r\n48 1034 PATEL  DEV  SHAILESHBHAI 20\r\n50 1068 PATEL  JENISHKUMAR  KISHORBHAI 24\r\n51 1035 PATEL  KHUSH  ALPESH 26\r\n52 1069 PATEL  KHUSHI  KALPESHBHAI 22\r\n53 1036 PATEL  MUHAMMADSHAKIL  MAHAMMAD  VAL 29\r\n54 1070 PATEL  PRINCEKUMAR  RAJESHBHAI 21\r\n55 1037 PATHAK  AADITYA  RAVINDRAPRASAD 23\r\n57 1038 PRAJAPATI  PRIYA  VASHRAMBHAI 22\r\n59 1040 RANA  PRATIKBHAI  KANTIBHAI 19\r\n60 1071 RATHOD  ANKITKUMAR  ANILBHAI 15\r\n61 1041 RATHOD  YAGNIK  SURESHBHAI 28\r\n62 1042 RAWAL  PRERANA  MAHESHKUMAR 15\r\n63 1072 SALI  PRATHAM  KIRAN 25\r\n64 1072 SALUNKE  DHRUV  VIKASBHAI 28\r\n65 1043 SARANG  RISHIT  BHUPENDRA 20\r\n66 1044 SODAVADIYA  YASH  MUKESHBHAI 26\r\n68 1045 SOPARIWALA  SHREYA  HITENKUMAR 19\r\n69 1046 TOPIWALA  KRISH  KALPESHBHAI 28\r\n70 1047 VARIYA  RUTVIKKUMAR  CHATURBHAI 28\r\n71 1074 VASOYA  DHANVI  DILIPBHAI 22\r\n72 1048 VASTARPARA  PRIYANKABEN  VIPULBHAI 28\r\n73 1049 VAVADIYA  DHRUVI  MAHESHBHAI 25\r\n74 1050 VELWAN  HARI  ANILKUMAR 22\r\n75 1051 YADAV  AVANISH  RAMASHANKAR 25\r\n76 1055 GOUDA  SOMNATH  UPENDRA 19\r\n77 1028 PADHIYAR  NAYAN  PRAKASHBHAI 19\r\n78 1025 MISTRI  VATSAL  RAKESHBHAI 19\r\n79 1027 OZA  NIRAJ  BINTUKUMAR 24\r\n80 1020 MANSURI  MOHAMMAD  AMAN 19\r\n81 1067 PATEL  ARYAN  HEMANTBHAI 19\r\n82 1022 MAURYA  ATUL  VISHRAM 1\r\n83 1052 CHAUDHARI  HETAL  VIJAYBHAI 19\r\n85 1039 RAMPHALE  NAMAN  RITESHBHAI 18\r\n86 1019 KHAKHIWALA  KUSH  MANISHBHAI 18\r\n', '2024-12-18 19:15:17', 2, '2024-12-18 19:15:17', 12),
(43, '12 26 17 16 17 104\r\n12 22 21 19 17 112\r\n15 23 19 17 17 111\r\n21 22 24 20 17 128\r\n20 23 20 30 17 136\r\n15 20 14 17 17 105\r\n27 26 20 28 27 157\r\n16 20 19 16 17 109\r\n22 24 19 30 27 145\r\n23 24 22 19 19 129\r\n24 23 24 21 21 132\r\n16 20 17 24 20 112\r\n21 23 22 21 18 133\r\n12 20 15 17 15 94\r\n21 26 22 24 21 139\r\n26 18 22 19 18 131\r\n12 23 18 21 20 114\r\n22 26 26 25 27 152\r\n13 17 15 18 17 99\r\n21 25 21 20 21 136\r\n19 22 18 22 19 128\r\n27 25 26 21 21 142\r\n25 28 26 27 27 161\r\n28 27 26 19 23 148\r\n19 27 27 24 22 141\r\n20 20 17 19 19 120\r\n12 17 15 19 21 103\r\n14 23 19 19 19 113\r\n13 21 16 20 23 112\r\n22 20 16 28 27 137\r\n13 18 17 17 19 103\r\n12 12 13 26 20 102\r\n1 1 1 1 1 6\r\n13 17 13 15 17 94\r\n12 18 12 17 15 92\r\n12 20 20 17 17 104\r\n', '2024-12-18 19:15:17', 4, '2024-12-18 19:15:17', 12),
(44, 'Roll  No.\r\nExam \r\nSeat \r\nNo\r\nNAME\r\n101\r\n(ReactJS)\r\n1 1001 ADHIYA  ZEEL  RAJESH 23\r\n2 1002 ADROJA  NIDHI  JITENDRABHAI 19\r\n3 1003 ARIWALA  PREM  MAYANKKUMAR 14\r\n4 1004 BARDOLIA  VAIBHAVI  ANISHKUMAR 25\r\n5 1005 BAROT  DHRUVI  DIPAKBHAI 27\r\n7 1006 BHARUCHA  MEET  SANJAY 21\r\n8 1007 BHIMANI  KEYUR  JITENDRABHAI 25\r\n10 1053 DAMWALA  SHOBHIT  DILIPKUMAR 26\r\n12 1008 DESAI  DRASHTI  SURESHBHAI 22\r\n13 1009 DHAMELIYA  ISHA  SURESHBHAI 20\r\n14 1010 DHODIYA  KRUPALI  RAKESHBHAI 19\r\n15 1054 DUDHAT  DHRUVINKUMAR  ARVINDBHAI 14\r\n16 1011 GODHANI  KHUSHI  SHAILESHBHAI 22\r\n18 1056 GUPTA  OM  RAJESH 21\r\n19 1012 HIRAWALA  NIDHI  VIPULKUMAR 12\r\n20 1057 JADHAV  SWATI  NARENDRA 24\r\n21 1013 JADHAV  VAIBHAV  VIKASSINGH 23\r\n22 1014 JARIWALA  ISHA  PARESHKUMAR 21\r\n23 1015 JARIWALA  NENCY  RITESHKUMAR 23\r\n24 1016 JARIWALA  UNNATI  BHAVESH 25\r\n26 1017 KANANI  DIVYESH  JAGDISHBHAI 18\r\n27 1018 KANTHARIYA  HENALKUMAR  DILIPBHAI 20\r\n28 1058 MALI  PAVAN  KAILAS 22\r\n30 1059 MASHRUWALA  SHANI  NIKESHBHAI 26\r\n31 1021 MAURYA  AKASH  RADHE  SHYAM 25\r\n33 1023 MAVANI  MITALI  HARESHBHAI 14\r\n34 1060 MEHTA  HENIL  KAMESHKUMAR 21\r\n35 1024 MEHTA  VISHWA  HARIOMBHAI 15\r\n36 1026 MODI  HEMIL  UMESHKUMAR 7\r\n37 1061 MODI  SUJAL  JIGNESH 16\r\n38 1062 MODI  VISHWA  MANISHKUMAR 25\r\n39 1063 NADAR  JENIFER  JAYKUMAR 17\r\n40 1064 PALADIYA  ASTI  DINESHBHAI 27\r\n41 1029 PANCHAL  DIYA  VIJAYBHAI 18\r\n42 1030 PANDIT  KHUSHI  DEVENDRA 20\r\n43 1031 PANKHIWALA  HARSH  NISHILKUMAR 19\r\n44 1032 PAREKH  KUSH  PINAKIN 26\r\n45 1065 PARMAR  CHIRAGBHAI  BHARATBHAI 20\r\nM.Sc.  ICT  1st  Sem  Internal  Theory  Examination  2024  (out  of  30)\r\n', '2024-12-18 19:15:17', 1, '2024-12-18 19:15:17', 12),
(45, 'HK\r\nMobile & Computer\r\nSales Service\r\nkhalidmansuri2683@gmail.com\r\nHK Mobile & Computer\r\nSales & Service\r\nhttps://g.co/kgs/Cu6YLM7\r\nA.Khalik Mansuri\r\n+91 9662730596\r\n', '2024-12-18 19:20:12', 1, '2024-12-18 19:20:12', 13),
(46, 'stats_installs_installs_com.wbv\r\nDate\r\nActive Device \r\nInstalls\r\nInstall \r\nevents\r\nUpdate \r\nevents\r\nUninstall \r\nevents\r\n2024-11-01 1330 186 77 37\r\n2024-11-02 1466 119 54 19\r\n2024-11-03 1566 149 42 36\r\n2024-11-04 1611 92 105 35\r\n2024-11-05 1690 62 199 27\r\n2024-11-06 1712 51 128 12\r\n2024-11-07 1735 43 113 17\r\n2024-11-08 1767 45 95 14\r\n2024-11-09 1772 35 69 15\r\n2024-11-10 1809 26 71 15\r\n2024-11-11 1835 46 33 16\r\n2024-11-12 1861 32 46 14\r\n2024-11-13 1903 39 42 11\r\n2024-11-14 1898 29 34 8\r\n2024-11-15 1915 33 39 15\r\n2024-11-16 1943 48 25 18\r\n2024-11-17 1966 32 28 11\r\n2024-11-18 1974 43 11 17\r\n2024-11-19 2017 42 10 11\r\n2024-11-20 2033 36 24 19\r\n2024-11-21 2093 45 16 12\r\n2024-11-22 2106 33 8 17\r\n2024-11-23 2116 50 13 18\r\n2024-11-24 2138 29 11 12\r\n2024-11-25 2142 23 6 11\r\n2024-11-26 2160 23 2 11\r\nPage 2\r\n', '2024-12-18 19:23:28', 2, '2024-12-18 19:23:28', 14),
(47, 'stats_installs_installs_com.wbv\r\nDate App Name\r\nDaily Device \r\nInstalls\r\nDaily User \r\nInstalls\r\nDaily User \r\nUninstalls\r\n2024-11-01 WBVF APP 154 124 37\r\n2024-11-02 WBVF APP 103 91 18\r\n2024-11-03 WBVF APP 124 116 39\r\n2024-11-04 WBVF APP 79 67 35\r\n2024-11-05 WBVF APP 45 32 26\r\n2024-11-06 WBVF APP 39 32 13\r\n2024-11-07 WBVF APP 41 33 18\r\n2024-11-08 WBVF APP 36 26 15\r\n2024-11-09 WBVF APP 25 25 14\r\n2024-11-10 WBVF APP 19 19 14\r\n2024-11-11 WBVF APP 37 32 12\r\n2024-11-12 WBVF APP 29 25 14\r\n2024-11-13 WBVF APP 32 28 10\r\n2024-11-14 WBVF APP 22 18 7\r\n2024-11-15 WBVF APP 25 24 14\r\n2024-11-16 WBVF APP 41 33 19\r\n2024-11-17 WBVF APP 26 24 10\r\n2024-11-18 WBVF APP 32 30 18\r\n2024-11-19 WBVF APP 39 35 11\r\n2024-11-20 WBVF APP 29 27 18\r\n2024-11-21 WBVF APP 35 33 10\r\n2024-11-22 WBVF APP 32 23 14\r\n2024-11-23 WBVF APP 33 37 17\r\n2024-11-24 WBVF APP 24 26 12\r\n2024-11-25 WBVF APP 17 13 11\r\n2024-11-26 WBVF APP 21 16 10\r\nPage 1\r\n', '2024-12-18 19:23:28', 1, '2024-12-18 19:23:28', 14),
(48, 'stats_installs_installs_com.wbv\r\nDate App Name\r\nDaily Device \r\nInstalls\r\nDaily User \r\nInstalls\r\nDaily User \r\nUninstalls\r\n2024-11-01 WBVF APP 154 124 37\r\n2024-11-02 WBVF APP 103 91 18\r\n2024-11-03 WBVF APP 124 116 39\r\n2024-11-04 WBVF APP 79 67 35\r\n2024-11-05 WBVF APP 45 32 26\r\n2024-11-06 WBVF APP 39 32 13\r\n2024-11-07 WBVF APP 41 33 18\r\n2024-11-08 WBVF APP 36 26 15\r\n2024-11-09 WBVF APP 25 25 14\r\n2024-11-10 WBVF APP 19 19 14\r\n2024-11-11 WBVF APP 37 32 12\r\n2024-11-12 WBVF APP 29 25 14\r\n2024-11-13 WBVF APP 32 28 10\r\n2024-11-14 WBVF APP 22 18 7\r\n2024-11-15 WBVF APP 25 24 14\r\n2024-11-16 WBVF APP 41 33 19\r\n2024-11-17 WBVF APP 26 24 10\r\n2024-11-18 WBVF APP 32 30 18\r\n2024-11-19 WBVF APP 39 35 11\r\n2024-11-20 WBVF APP 29 27 18\r\n2024-11-21 WBVF APP 35 33 10\r\n2024-11-22 WBVF APP 32 23 14\r\n2024-11-23 WBVF APP 33 37 17\r\n2024-11-24 WBVF APP 24 26 12\r\n2024-11-25 WBVF APP 17 13 11\r\n2024-11-26 WBVF APP 21 16 10\r\nPage 1\r\n', '2024-12-18 21:38:55', 1, '2024-12-18 21:38:55', 15),
(49, 'stats_installs_installs_com.wbv\r\nDate\r\nActive Device \r\nInstalls\r\nInstall \r\nevents\r\nUpdate \r\nevents\r\nUninstall \r\nevents\r\n2024-11-01 1330 186 77 37\r\n2024-11-02 1466 119 54 19\r\n2024-11-03 1566 149 42 36\r\n2024-11-04 1611 92 105 35\r\n2024-11-05 1690 62 199 27\r\n2024-11-06 1712 51 128 12\r\n2024-11-07 1735 43 113 17\r\n2024-11-08 1767 45 95 14\r\n2024-11-09 1772 35 69 15\r\n2024-11-10 1809 26 71 15\r\n2024-11-11 1835 46 33 16\r\n2024-11-12 1861 32 46 14\r\n2024-11-13 1903 39 42 11\r\n2024-11-14 1898 29 34 8\r\n2024-11-15 1915 33 39 15\r\n2024-11-16 1943 48 25 18\r\n2024-11-17 1966 32 28 11\r\n2024-11-18 1974 43 11 17\r\n2024-11-19 2017 42 10 11\r\n2024-11-20 2033 36 24 19\r\n2024-11-21 2093 45 16 12\r\n2024-11-22 2106 33 8 17\r\n2024-11-23 2116 50 13 18\r\n2024-11-24 2138 29 11 12\r\n2024-11-25 2142 23 6 11\r\n2024-11-26 2160 23 2 11\r\nPage 2\r\n', '2024-12-18 21:38:55', 2, '2024-12-18 21:38:55', 15);

-- --------------------------------------------------------

--
-- Table structure for table `notes_view`
--

CREATE TABLE `notes_view` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `notes_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `note_comments`
--

CREATE TABLE `note_comments` (
  `id` int(11) NOT NULL,
  `comment_text` longtext DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `note_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `note_comments`
--

INSERT INTO `note_comments` (`id`, `comment_text`, `created_at`, `note_id`, `user_id`) VALUES
(3, 'Hello Dosto', '2024-11-15 16:23:19', 1, 21),
(4, 'sfd', '2024-11-26 15:30:20', 1, 21),
(5, 'sd br4twe', '2024-11-26 15:31:51', 1, 21);

-- --------------------------------------------------------

--
-- Table structure for table `note_replies`
--

CREATE TABLE `note_replies` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `reply_text` longtext DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `post_comments`
--

CREATE TABLE `post_comments` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `text` longtext DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `post_comments`
--

INSERT INTO `post_comments` (`id`, `created_at`, `text`, `updated_at`, `post_id`, `user_id`) VALUES
(1, '2024-12-19 19:06:52', 'Assalamualaikum', '2024-12-19 19:06:52', 1, 31),
(2, '2024-12-19 19:12:54', 'How Are You ???', '2024-12-19 19:12:54', 1, 31),
(3, '2024-12-19 19:13:41', 'Looking Perfect....', '2024-12-19 19:13:41', 1, 31),
(4, '2024-12-19 19:20:34', 'Assalamualaikum Friends.....', '2024-12-19 19:20:34', 2, 34),
(5, '2024-12-19 19:21:38', 'we need to work with you', '2024-12-19 19:21:38', 1, 34),
(6, '2024-12-20 10:05:12', 'Hey There', '2024-12-20 10:05:12', 3, 31),
(7, '2024-12-20 10:08:22', 'Looks So Good!!!', '2024-12-20 10:08:22', 3, 31),
(8, '2024-12-20 18:31:55', 'Hey Bro', '2024-12-20 18:31:55', 1, 35),
(9, '2024-12-21 11:22:55', 'Waahhhh.....', '2024-12-21 11:22:55', 1, 31),
(10, '2024-12-21 12:52:48', 'Hello', '2024-12-21 12:52:48', 4, 31);

-- --------------------------------------------------------

--
-- Table structure for table `post_feed_mst`
--

CREATE TABLE `post_feed_mst` (
  `id` int(11) NOT NULL,
  `caption` longtext DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `post_feed_mst`
--

INSERT INTO `post_feed_mst` (`id`, `caption`, `created_at`, `location`, `photo`, `updated_at`, `user_id`) VALUES
(1, 'First Post Is This', '2024-12-18 23:27:08', 'Home', '18Dec2024112617PM_NBX_Snapshot_2024-11-26_17-25-44-236.png', '2024-12-18 23:27:08', 31),
(2, 'Hey This is the starting of the ERA of Tech Savvy Solution', '2024-12-18 23:57:22', 'Tech Savvy', '18Dec2024115715PM_header_v2.png', '2024-12-18 23:57:22', 34),
(3, 'Our Official Logo', '2024-12-19 19:24:33', 'Surat', '19Dec2024072427PM_logo_irg.png', '2024-12-19 19:24:33', 34),
(4, 'Marathon launched', '2024-12-21 12:52:10', 'Surat', '21Dec2024125205PM_1000120451.jpg', '2024-12-21 12:52:10', 31),
(5, 'Tesr', '2024-12-21 13:33:45', 'Test', '21Dec2024013334PM_deposit.png', '2024-12-21 13:33:45', 31);

-- --------------------------------------------------------

--
-- Table structure for table `post_likes`
--

CREATE TABLE `post_likes` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `post_likes`
--

INSERT INTO `post_likes` (`id`, `created_at`, `updated_at`, `post_id`, `user_id`) VALUES
(2, '2024-12-19 13:19:58', '2024-12-19 13:19:58', 1, 32),
(18, '2024-12-19 17:57:29', '2024-12-19 17:57:29', 2, 31),
(23, '2024-12-20 10:08:28', '2024-12-20 10:08:28', 3, 31),
(25, '2024-12-20 13:54:47', '2024-12-20 13:54:47', 1, 34),
(27, '2024-12-20 18:32:05', '2024-12-20 18:32:05', 1, 35),
(28, '2024-12-21 11:22:42', '2024-12-21 11:22:42', 1, 31),
(29, '2024-12-21 12:52:33', '2024-12-21 12:52:33', 4, 31),
(30, '2024-12-21 15:03:49', '2024-12-21 15:03:49', 5, 31);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `blocked_until` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `is_blocked` tinyint(1) DEFAULT 0,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profile` longtext DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `blocked_until`, `created_at`, `email`, `is_active`, `is_blocked`, `name`, `password`, `phone`, `profile`, `updated_at`, `username`) VALUES
(21, NULL, '2024-11-06 11:28:44', 'shakil@gmail.com', 1, 0, 'Shakil Patel', 'PBKDF2WithHmacSHA256:2048:iUnvXMK0SH+RYbPBMXO07xTZ8LDO0K7/UWhP85m3YdU=:r2WJqOS3nmKfLY3Rs5KEenDGsoNj/Q9k3H7Xiuv2eBY=', '1234367890', 'laptop.png', '2024-11-06 11:28:44', 'patelshakil'),
(22, NULL, '2024-11-14 13:19:52', 'samir@gmail.com', 1, 0, 'Samir Patel', 'PBKDF2WithHmacSHA256:2048:PfYdnwJU0nq2shNzCHSYdrwMA8OFAeTtfGMVunIdFh8=:6/RWdma3AhzCuHhIi/xI8ze106T1alLz0ceRiF7mk2c=', '70.00', NULL, '2024-11-14 13:19:52', 'samirpatel'),
(27, NULL, '2024-11-14 18:50:20', 'testuser@gmail.com', 1, 0, 'Test user', 'PBKDF2WithHmacSHA256:2048:uQLJYoa6QYAISA9JK4m+HgoO1WhfeDeDocZKke+JerE=:FzxWqxieXJMGLAw99SLoOCM7InEet+mc2pJzfJmJCIM=', '0987654321', NULL, '2024-11-14 18:50:20', 'testuser'),
(28, NULL, '2024-11-14 18:58:22', 'aamir@gmail.com', 1, 0, 'Aamir', 'PBKDF2WithHmacSHA256:2048:HQZ6H4RqtM4tCbd3HjcUJKuEP6uzOwHV12xnBKpfrDE=:6hAJ/vreWJUO7uqqud8BrGeGeNF+wFLQ+tqWaGWXthw=', '1234567890', NULL, '2024-11-14 18:58:22', 'aamir'),
(29, NULL, '2024-11-14 19:19:18', 'abcd@gmail.com', 1, 0, 'Test user 1', 'PBKDF2WithHmacSHA256:2048:/uRd/3AwVzlcYDczkrBRfoQwlv8TG+zeXfSQ0BHX6rY=:DcfWWitu42rdXW+2FHRfK8omZUZapfLO7McFhBhzGl4=', '109284831', '14Nov2024071801PM_photo_2023-07-22_22-50-07.jpg', '2024-11-14 19:19:18', 'testuser1'),
(30, NULL, '2024-11-14 19:27:38', 'abcds@gmail.com', 1, 0, 'Test user 2', 'PBKDF2WithHmacSHA256:2048:Ql+I7o32wktbNe1+1ODvlBoCQKREtP5FzOvUTWKOTHg=:6RhK0EF2Ch8OkzeBk9Ef+LgfFiYG78+ISezN0OmpOzM=', '1234527890', '14Nov2024072715PM_samir.png', '2024-11-14 19:27:38', 'testuser2'),
(31, NULL, '2024-11-25 20:51:33', 'patelsakib95@gmail.com', 1, 0, 'Dummy Jwt', 'PBKDF2WithHmacSHA256:2048:ZmtKg/lGJy6TcFoUfl0XOpfdblkbqLkhDqUWtLLMO9M=:ytbJp7wjV3EJzxQ7YLhH/uI4jZgc0LuGWCbWZyfi6yA=', '1231231230', '13Dec2024010413PM193541.png', '2024-11-25 20:51:33', 'dummyjwt'),
(32, NULL, '2024-12-13 13:04:55', 'pathansample@gmail.com', 1, 0, 'Pathan Sample', 'PBKDF2WithHmacSHA256:2048:mX7vUkrUwcYstIFmHr4rCWeYjXrgjIz8KfsKXa+knMI=:iRDLHL1w0mHUR+N764XZo7/QLPGwO9M/gcuHu4zPyTU=', '1012301232', '13Dec2024010413PM193541.png', '2024-12-13 13:04:55', 'pathan'),
(34, NULL, '2024-12-15 22:30:09', 'tech.savvy.it.solution@gmail.com', 1, 0, 'Tech Savvy Solution', 'PBKDF2WithHmacSHA256:2048:Tnoox5teQJQHRAHnJ9iAxSg1+7FenolEQFpqaikyVVI=:oLE/zK/mbxKDQJtxslLcW9iD4OVZgX4xzR8GzJxlA+E=', '7041172825', '15Dec2024102930PM_Screenshot_2024-05-25_231254.png', '2024-12-15 22:30:09', 'techsavvysolution'),
(35, NULL, '2024-12-16 07:28:59', 'admin@gmail.com', 1, 0, 'Admin', 'PBKDF2WithHmacSHA256:2048:xr55hYm6iJQTTZlx15MB39TxlqaJEZIBVLNTOd4wPCU=:FogtW/qWVVb17b1eaqxCFOJ3XE5a3LSnrbw/8NvQg+s=', '101010101010', '16Dec2024072824AM_NBX_Snapshot_2024-11-26_17-25-44-236.png', '2024-12-16 07:28:59', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `user_settings`
--

CREATE TABLE `user_settings` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `is_chat_enabled` tinyint(1) DEFAULT 0,
  `is_community_enabled` tinyint(1) DEFAULT 0,
  `is_feed_enabled` tinyint(1) DEFAULT 0,
  `is_notes_enabled` tinyint(1) DEFAULT 0,
  `is_private` tinyint(1) DEFAULT 0,
  `updated_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_settings`
--

INSERT INTO `user_settings` (`id`, `created_at`, `is_chat_enabled`, `is_community_enabled`, `is_feed_enabled`, `is_notes_enabled`, `is_private`, `updated_at`, `user_id`) VALUES
(2, '2024-11-06 11:28:44', 0, 1, 1, 1, 0, '2024-11-06 11:28:44', 21),
(3, '2024-11-14 13:19:53', NULL, NULL, NULL, NULL, 0, '2024-11-14 13:19:53', 22),
(8, '2024-11-14 18:50:21', NULL, NULL, NULL, NULL, 0, '2024-11-14 18:50:21', 27),
(9, '2024-11-14 18:58:22', NULL, NULL, NULL, NULL, 0, '2024-11-14 18:58:22', 28),
(10, '2024-11-14 19:19:18', NULL, NULL, NULL, NULL, 0, '2024-11-14 19:19:18', 29),
(11, '2024-11-14 19:27:38', NULL, NULL, NULL, NULL, 0, '2024-11-14 19:27:38', 30),
(12, '2024-11-25 20:51:34', NULL, NULL, NULL, NULL, 0, '2024-11-25 20:51:34', 31),
(13, '2024-12-13 13:04:56', NULL, NULL, NULL, NULL, 0, '2024-12-13 13:04:56', 32),
(15, '2024-12-15 22:30:09', NULL, NULL, NULL, NULL, 0, '2024-12-15 22:30:09', 34),
(16, '2024-12-16 07:28:59', NULL, NULL, NULL, NULL, 0, '2024-12-16 07:28:59', 35);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `community_members`
--
ALTER TABLE `community_members`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_community_members_community_id` (`community_id`),
  ADD KEY `FK_community_members_user_id` (`user_id`);

--
-- Indexes for table `community_msg`
--
ALTER TABLE `community_msg`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_community_msg_community_id` (`community_id`),
  ADD KEY `FK_community_msg_user_id` (`user_id`);

--
-- Indexes for table `community_mst`
--
ALTER TABLE `community_mst`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_community_mst_user_id` (`user_id`);

--
-- Indexes for table `community_reply`
--
ALTER TABLE `community_reply`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_community_reply_user_id` (`user_id`),
  ADD KEY `FK_community_reply_community_msg_id` (`community_msg_id`);

--
-- Indexes for table `faq_answers`
--
ALTER TABLE `faq_answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_faq_answers_faq_id` (`faq_id`),
  ADD KEY `FK_faq_answers_user_id` (`user_id`);

--
-- Indexes for table `faq_mst`
--
ALTER TABLE `faq_mst`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_faq_mst_user_id` (`user_id`);

--
-- Indexes for table `faq_screenshot`
--
ALTER TABLE `faq_screenshot`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_faq_screenshot_faq_id` (`faq_id`);

--
-- Indexes for table `faq_votes`
--
ALTER TABLE `faq_votes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_faq_votes_faq_id` (`faq_ans_id`),
  ADD KEY `FK_faq_votes_user_id` (`user_id`);

--
-- Indexes for table `group_mst`
--
ALTER TABLE `group_mst`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_users`
--
ALTER TABLE `group_users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_group_users_user_id` (`user_id`),
  ADD KEY `FK_group_users_group_id` (`group_id`);

--
-- Indexes for table `msg_mst`
--
ALTER TABLE `msg_mst`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_msg_mst_receiver_id` (`receiver_id`),
  ADD KEY `FK_msg_mst_sender_id` (`sender_id`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_notes_user_id` (`user_id`);

--
-- Indexes for table `notes_text`
--
ALTER TABLE `notes_text`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_notes_text_notes_id` (`notes_id`);

--
-- Indexes for table `notes_view`
--
ALTER TABLE `notes_view`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_notes_view_notes_id` (`notes_id`),
  ADD KEY `FK_notes_view_user_id` (`user_id`);

--
-- Indexes for table `note_comments`
--
ALTER TABLE `note_comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_note_comments_user_id` (`user_id`),
  ADD KEY `FK_note_comments_note_id` (`note_id`);

--
-- Indexes for table `note_replies`
--
ALTER TABLE `note_replies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_note_replies_user_id` (`user_id`),
  ADD KEY `FK_note_replies_comment_id` (`comment_id`);

--
-- Indexes for table `post_comments`
--
ALTER TABLE `post_comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_post_comments_user_id` (`user_id`),
  ADD KEY `FK_post_comments_post_id` (`post_id`);

--
-- Indexes for table `post_feed_mst`
--
ALTER TABLE `post_feed_mst`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_post_feed_mst_user_id` (`user_id`);

--
-- Indexes for table `post_likes`
--
ALTER TABLE `post_likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_post_likes_post_id` (`post_id`),
  ADD KEY `FK_post_likes_user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `user_settings`
--
ALTER TABLE `user_settings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_user_settings_user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `community_members`
--
ALTER TABLE `community_members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `community_msg`
--
ALTER TABLE `community_msg`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `community_mst`
--
ALTER TABLE `community_mst`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `community_reply`
--
ALTER TABLE `community_reply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `faq_answers`
--
ALTER TABLE `faq_answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `faq_mst`
--
ALTER TABLE `faq_mst`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `faq_screenshot`
--
ALTER TABLE `faq_screenshot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `faq_votes`
--
ALTER TABLE `faq_votes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `group_mst`
--
ALTER TABLE `group_mst`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `group_users`
--
ALTER TABLE `group_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `msg_mst`
--
ALTER TABLE `msg_mst`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `notes_text`
--
ALTER TABLE `notes_text`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `notes_view`
--
ALTER TABLE `notes_view`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `note_comments`
--
ALTER TABLE `note_comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `note_replies`
--
ALTER TABLE `note_replies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post_comments`
--
ALTER TABLE `post_comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `post_feed_mst`
--
ALTER TABLE `post_feed_mst`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `post_likes`
--
ALTER TABLE `post_likes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `user_settings`
--
ALTER TABLE `user_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `community_members`
--
ALTER TABLE `community_members`
  ADD CONSTRAINT `FK_community_members_community_id` FOREIGN KEY (`community_id`) REFERENCES `community_mst` (`id`),
  ADD CONSTRAINT `FK_community_members_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `community_msg`
--
ALTER TABLE `community_msg`
  ADD CONSTRAINT `FK_community_msg_community_id` FOREIGN KEY (`community_id`) REFERENCES `community_mst` (`id`),
  ADD CONSTRAINT `FK_community_msg_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `community_mst`
--
ALTER TABLE `community_mst`
  ADD CONSTRAINT `FK_community_mst_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `community_reply`
--
ALTER TABLE `community_reply`
  ADD CONSTRAINT `FK_community_reply_community_msg_id` FOREIGN KEY (`community_msg_id`) REFERENCES `community_msg` (`id`),
  ADD CONSTRAINT `FK_community_reply_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `faq_answers`
--
ALTER TABLE `faq_answers`
  ADD CONSTRAINT `FK_faq_answers_faq_id` FOREIGN KEY (`faq_id`) REFERENCES `faq_mst` (`id`),
  ADD CONSTRAINT `FK_faq_answers_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `faq_mst`
--
ALTER TABLE `faq_mst`
  ADD CONSTRAINT `FK_faq_mst_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `faq_screenshot`
--
ALTER TABLE `faq_screenshot`
  ADD CONSTRAINT `FK_faq_screenshot_faq_id` FOREIGN KEY (`faq_id`) REFERENCES `faq_mst` (`id`);

--
-- Constraints for table `faq_votes`
--
ALTER TABLE `faq_votes`
  ADD CONSTRAINT `FK_faq_votes_faq_ans_id` FOREIGN KEY (`faq_ans_id`) REFERENCES `faq_answers` (`id`),
  ADD CONSTRAINT `FK_faq_votes_faq_id` FOREIGN KEY (`faq_ans_id`) REFERENCES `faq_answers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_faq_votes_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `group_users`
--
ALTER TABLE `group_users`
  ADD CONSTRAINT `FK_group_users_group_id` FOREIGN KEY (`group_id`) REFERENCES `group_mst` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_group_users_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `msg_mst`
--
ALTER TABLE `msg_mst`
  ADD CONSTRAINT `FK_msg_mst_receiver_id` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_msg_mst_sender_id` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `FK_notes_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `notes_text`
--
ALTER TABLE `notes_text`
  ADD CONSTRAINT `FK_notes_text_notes_id` FOREIGN KEY (`notes_id`) REFERENCES `notes` (`id`);

--
-- Constraints for table `notes_view`
--
ALTER TABLE `notes_view`
  ADD CONSTRAINT `FK_notes_view_notes_id` FOREIGN KEY (`notes_id`) REFERENCES `notes` (`id`),
  ADD CONSTRAINT `FK_notes_view_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `note_comments`
--
ALTER TABLE `note_comments`
  ADD CONSTRAINT `FK_note_comments_note_id` FOREIGN KEY (`note_id`) REFERENCES `notes` (`id`),
  ADD CONSTRAINT `FK_note_comments_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `note_replies`
--
ALTER TABLE `note_replies`
  ADD CONSTRAINT `FK_note_replies_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `note_comments` (`id`),
  ADD CONSTRAINT `FK_note_replies_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `post_comments`
--
ALTER TABLE `post_comments`
  ADD CONSTRAINT `FK_post_comments_post_id` FOREIGN KEY (`post_id`) REFERENCES `post_feed_mst` (`id`),
  ADD CONSTRAINT `FK_post_comments_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `post_feed_mst`
--
ALTER TABLE `post_feed_mst`
  ADD CONSTRAINT `FK_post_feed_mst_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `post_likes`
--
ALTER TABLE `post_likes`
  ADD CONSTRAINT `FK_post_likes_post_id` FOREIGN KEY (`post_id`) REFERENCES `post_feed_mst` (`id`),
  ADD CONSTRAINT `FK_post_likes_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `user_settings`
--
ALTER TABLE `user_settings`
  ADD CONSTRAINT `FK_user_settings_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
