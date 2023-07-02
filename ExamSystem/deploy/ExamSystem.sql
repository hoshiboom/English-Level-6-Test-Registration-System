/*
 Navicat Premium Data Transfer

 Source Server         : hoshiboom
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : hoshiboom.space:3306
 Source Schema         : ExamSystem

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 02/07/2023 16:05:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (2, 'hi', '123456', '2012618', 'hi');
INSERT INTO `admin` VALUES (3, '123', '123', '123', '123');
INSERT INTO `admin` VALUES (4, 'lkx', '123456', '4321', '123456');
INSERT INTO `admin` VALUES (5, '12', '12', '', '');
INSERT INTO `admin` VALUES (6, '12', '12', '12', '12');

-- ----------------------------
-- Table structure for doandcheck
-- ----------------------------
DROP TABLE IF EXISTS `doandcheck`;
CREATE TABLE `doandcheck`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) NULL DEFAULT NULL,
  `paperinfo_id` int(0) NULL DEFAULT NULL,
  `question_id` int(0) NULL DEFAULT NULL,
  `student_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `actual_score` int(0) NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT NULL COMMENT '0未作答1已作答2已批改',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doandcheck
-- ----------------------------
INSERT INTO `doandcheck` VALUES (1, 1, 1, 8, 'student 1 article', 1, 2);
INSERT INTO `doandcheck` VALUES (2, 1, 1, 9, 'student 1 translation', 12, 2);
INSERT INTO `doandcheck` VALUES (3, 2, 2, 8, 'student 2 article', 2, 1);
INSERT INTO `doandcheck` VALUES (4, 2, 2, 9, 'student 2 translation', 1, 1);

-- ----------------------------
-- Table structure for paperinfo
-- ----------------------------
DROP TABLE IF EXISTS `paperinfo`;
CREATE TABLE `paperinfo`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `money` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paperinfo
-- ----------------------------
INSERT INTO `paperinfo` VALUES (1, '50', '六级', NULL, '2023.7.1');

-- ----------------------------
-- Table structure for paperorg
-- ----------------------------
DROP TABLE IF EXISTS `paperorg`;
CREATE TABLE `paperorg`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `question_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `paperinfo_id` int(0) NULL DEFAULT NULL,
  `order_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paperorg
-- ----------------------------
INSERT INTO `paperorg` VALUES (4, '28', 1, 1);
INSERT INTO `paperorg` VALUES (5, '29', 1, 2);
INSERT INTO `paperorg` VALUES (6, '30', 1, 3);
INSERT INTO `paperorg` VALUES (7, '31', 1, 4);
INSERT INTO `paperorg` VALUES (8, '32', 1, 5);
INSERT INTO `paperorg` VALUES (9, '33', 1, 6);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `question_name` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `question_description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `option_a` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `option_b` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `option_c` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `option_d` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type_id` int(0) NULL DEFAULT NULL COMMENT '1选择2填空3翻译4作文（1、2可以自动阅卷）',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '1', '1', '1', '1', '1', '1', 2, 'hello', 10);
INSERT INTO `question` VALUES (2, '2', '', '', '', '', '', 2, '2', 2);
INSERT INTO `question` VALUES (3, '测试问题', NULL, NULL, NULL, NULL, NULL, 4, NULL, NULL);
INSERT INTO `question` VALUES (4, '1', '1', '1', '1', '2', '2', 2, '2', 2);
INSERT INTO `question` VALUES (5, '1', '1', '1', '1', '1', '1', 2, '1', 1);
INSERT INTO `question` VALUES (6, '1', '1', '1', '1', '1', '1', 2, '2', 2);
INSERT INTO `question` VALUES (7, 'test1', '1', '1', '1', '1', '1', 2, '1', 1);
INSERT INTO `question` VALUES (8, '作文题', '测试前端', '', '', '', '', 4, '随意', 20);
INSERT INTO `question` VALUES (9, '翻译题', '测试前端1', '', '', '', '', 3, '随意', 10);
INSERT INTO `question` VALUES (10, '', '', '', '', '', '', 0, '', 5);
INSERT INTO `question` VALUES (11, '', '', '', '', '', '', 0, '', 5);
INSERT INTO `question` VALUES (12, '', '', '', '', '', '', 0, '', 5);
INSERT INTO `question` VALUES (13, '1', '2', '1', '1', '1', '1', 1, '1', 1);
INSERT INTO `question` VALUES (19, '\"填空测试\"', '\"填空测试\"', '\"No value\"', '\"No value\"', '\"No value\"', '\"No value\"', 2, '\"填空测试\"', 2);
INSERT INTO `question` VALUES (20, '选择题测试', '选择题测试', '选择题测试', '选择题测试', '选择题测试', '选择题测试', 1, 'A', 3);
INSERT INTO `question` VALUES (21, '测试正常输入', '测试正常输入', 'No value', 'No value', 'No value', 'No value', 3, '测试正常输入', 106);
INSERT INTO `question` VALUES (22, '测试选择题分值', '测试选择题分值', '测试选择题分值', '测试选择题分值', '测试选择题分值', '测试选择题分值', 1, 'A', 20);
INSERT INTO `question` VALUES (23, '阅读部分A,1', '1.According to the passage， what is the main cause of global warming?,1. According to the passage， what is the main cause of global warming?', 'Deforestation,Deforestation', 'Burning fossil fuels,Burning fossil fuels', 'Natural climate changes,Natural climate changes', 'null,null', 1, 'C,C', 2);
INSERT INTO `question` VALUES (24, '阅读部分A,1', '1.According to the passage， what is the main cause of global warming?,1. According to the passage， what is the main cause of global warming?', 'Deforestation,Deforestation', 'Burning fossil fuels,Burning fossil fuels', 'Natural climate changes,Natural climate changes', 'null,null', 1, 'C,C', 2);
INSERT INTO `question` VALUES (26, '阅读部分A,1', '1.According to the passage， what is the main cause of global warming?,1. According to the passage， what is the main cause of global warming?', 'Deforestation,Deforestation', 'Burning fossil fuels,Burning fossil fuels', 'Natural climate changes,Natural climate changes', 'null,null', 1, 'C,C', 2);
INSERT INTO `question` VALUES (27, '测试问题,1', '测试描述,1', '\"No value\",1', '\"No value\",1', '\"No value\",2', '\"No value\",2', 2, '\"填空测试\",2', 50);
INSERT INTO `question` VALUES (28, 'What is the main topic of the conversation?', NULL, 'Traveling to a new city', 'Trying new foods', 'Shopping for clothes', NULL, 1, 'A', 2);
INSERT INTO `question` VALUES (29, 'According to the passage， what is the main cause of global warming?', 'Global warming, the phenomenon of increasing average air temperatures near the surface of Earth over the past one to two centuries. Climate scientists have since the mid-20th century gathered detailed observations of various weather phenomena (such as temperatures, precipitation, and storms) and of related influences on climate (such as ocean currents and the atmosphere’s chemical composition). These data indicate that Earth’s climate has changed over almost every conceivable timescale since the beginning of geologic time and that human activities since at least the beginning of the Industrial Revolution have a growing influence over the pace and extent of present-day climate change.', 'Deforestation', 'Burning fossil fuels', 'Natural climate changes', NULL, 1, 'B', 5);
INSERT INTO `question` VALUES (30, 'What is the recommended amount of moderate-intensity aerobic activity for adults?', 'The importance of exercise for maintaining good health cannot be overstated. Regular physical activity has numerous benefits, including reducing the risk of chronic diseases, improving mental health, and increasing overall well-being. It is recommended that adults engage in at least 150 minutes of moderate-intensity aerobic activity or 75 minutes of vigorous-intensity aerobic activity per week, along with muscle-strengthening activities at least two days a week.', '60 minutes per week', '150 minutes per week', '300 minutes per week', NULL, 1, 'C', 5);
INSERT INTO `question` VALUES (31, 'What are some challenges associated with technology?', 'Technology has revolutionized the way we live and work. From smartphones to artificial intelligence, advancements in technology have brought about significant changes in various aspects of society. While technology offers numerous benefits, such as increased efficiency and convenience, it also presents challenges, such as privacy concerns and job displacement. It is important to strike a balance between embracing technological advancements and addressing their potential drawbacks.', 'Increased efficiency and convenience', 'Privacy concerns and job displacement', 'Positive impact on society', NULL, 1, 'A', 5);
INSERT INTO `question` VALUES (32, '瓷器(porcelain)是一种由精选的瓷土(porcelain clay)或瓷石(pottery stone)、通过工艺流程制成的物件。尽管瓷器由陶器(pottery)发展而来，然而它们在原料、轴(glaze)、烧制温度方面都是不同的。比起陶器，瓷器质地更坚硬，器身更通透，光泽也更好。瓷器在英语里叫做china,因为第 一件瓷器就是在中国制造的。瓷器促进了中国和外部世界之间的经济文化交流，并深刻地影响着其他国家的传统文化和生活方式。', NULL, NULL, NULL, NULL, NULL, 3, NULL, 50);
INSERT INTO `question` VALUES (33, 'Nowadays more and more people keep learning new skills to adapt to a fast-changing world.you can make comments,use examples,or use your personal experiences to develop your essay.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50);
INSERT INTO `question` VALUES (34, '阿斯蒂芬', '上的广告', '1234', '123', '12345', '135', 1, 'A', 1);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) NULL DEFAULT NULL,
  `paperinfo_id` int(0) NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT NULL COMMENT '是否批改完成，0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (2, 1, 1, 425, 2);
INSERT INTO `score` VALUES (3, 1, 2, 500, 1);
INSERT INTO `score` VALUES (4, 1, 3, 525, 1);
INSERT INTO `score` VALUES (5, 1, 1, 1, 1);

-- ----------------------------
-- Table structure for signup
-- ----------------------------
DROP TABLE IF EXISTS `signup`;
CREATE TABLE `signup`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) NULL DEFAULT NULL,
  `paperinfo_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of signup
-- ----------------------------
INSERT INTO `signup` VALUES (1, 1, 1);
INSERT INTO `signup` VALUES (3, 1, 1);
INSERT INTO `signup` VALUES (4, 1, 1);
INSERT INTO `signup` VALUES (5, 1, 1);
INSERT INTO `signup` VALUES (6, 10, 10);
INSERT INTO `signup` VALUES (7, 6523, 1);
INSERT INTO `signup` VALUES (8, 6523, 1);
INSERT INTO `signup` VALUES (9, 6523, 1);
INSERT INTO `signup` VALUES (10, 6523, 1);
INSERT INTO `signup` VALUES (11, 6523, 1);
INSERT INTO `signup` VALUES (12, 6523, 1);
INSERT INTO `signup` VALUES (13, 6523, 1);
INSERT INTO `signup` VALUES (14, 6523, 1);
INSERT INTO `signup` VALUES (15, 6523, 1);
INSERT INTO `signup` VALUES (16, 6523, 1);
INSERT INTO `signup` VALUES (17, 6523, 1);
INSERT INTO `signup` VALUES (18, 6523, 1);
INSERT INTO `signup` VALUES (19, 6523, 1);
INSERT INTO `signup` VALUES (20, 6523, 1);
INSERT INTO `signup` VALUES (21, 6523, 1);
INSERT INTO `signup` VALUES (22, 6523, 1);
INSERT INTO `signup` VALUES (23, 1, 1);
INSERT INTO `signup` VALUES (24, 6523, 1);
INSERT INTO `signup` VALUES (25, 12, 1);
INSERT INTO `signup` VALUES (26, 12, 1);
INSERT INTO `signup` VALUES (27, 12, 1);
INSERT INTO `signup` VALUES (28, 1, 1);
INSERT INTO `signup` VALUES (29, 6523, 1);
INSERT INTO `signup` VALUES (30, 43210, 1);
INSERT INTO `signup` VALUES (31, 43210, 1);
INSERT INTO `signup` VALUES (32, 2013851, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '准考证号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '1', '13256', '6523', '1', '21', '23');
INSERT INTO `student` VALUES (2, '1', '12', '123', '12345', '123456', '1234');
INSERT INTO `student` VALUES (3, 'helloo', '1234', '1234', '1234', '1234', '4');
INSERT INTO `student` VALUES (5, 's', 'd', '12345678', '12345678', '12344', '44');
INSERT INTO `student` VALUES (7, 's', 'd', '45678990', '123456789', '12344', '44');
INSERT INTO `student` VALUES (9, '123', '123554', '448892', '5679', '', '151856');
INSERT INTO `student` VALUES (14, '123', '123554', '44845', '56775', '', '151856');
INSERT INTO `student` VALUES (15, '123', '123554', '44845111', '5677511', '1111', '151856');
INSERT INTO `student` VALUES (23, 'hi', '123', '2134232', '12341', '21442', '123456');
INSERT INTO `student` VALUES (31, '张三', 'Lkx020426', '2013851', '220503123412341234', '2308327655@qq.com', '15943548836');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '2', '2', 2, '2', '2', '');
INSERT INTO `teacher` VALUES (2, 'lkx', '123456', 2013851, '1223', '11111', '1234567890');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------

-- ----------------------------
-- Table structure for wrong
-- ----------------------------
DROP TABLE IF EXISTS `wrong`;
CREATE TABLE `wrong`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `stuId` int(0) NULL DEFAULT NULL,
  `questionId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `paperId` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wrong
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
