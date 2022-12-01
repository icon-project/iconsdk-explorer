/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 icon_mainnet.T_ADDRESS 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_ADDRESS` (
    `address` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'address / 주소',
    `balance` varchar(32) DEFAULT NULL COMMENT 'icx balance / 잔액',
    `tx_count` int(11) unsigned DEFAULT NULL COMMENT 'transaction count  / 트랜잭션 개수',
    `node_type` varchar(10) DEFAULT NULL COMMENT 'node_type  / 노드 타입',
    `balance_order` decimal(64,18) DEFAULT NULL COMMENT '잔액순 정렬용',
    PRIMARY KEY (`address`),
    KEY `balance_order` (`balance_order`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='top Icx Holder list / 상위 ICX 홀더 리스트 ';


-- 테이블 icon_mainnet.T_ADDRESS_HISTORY 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_ADDRESS_HISTORY` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `balance_address_count` int(11) unsigned DEFAULT '0' COMMENT '유효 지갑주소 개수',
    `total_address_count` int(11) unsigned DEFAULT '0' COMMENT '전체 지갑주소 개수',
    `standard_date` date DEFAULT NULL COMMENT '수집기준일, yyyy-MM-dd',
    `create_date` datetime DEFAULT NULL COMMENT '등록 날짜 ',
    PRIMARY KEY (`id`),
    UNIQUE KEY `standard_date` (`standard_date`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_ADDRESS_PUSH 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_ADDRESS_PUSH` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `address` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'address / 주소',
    `endpoint` varchar(256) DEFAULT NULL COMMENT 'push용 url',
    `p256dh` varchar(256) DEFAULT NULL COMMENT 'key_p256dh',
    `auth` varchar(256) DEFAULT NULL COMMENT 'key_auth',
    `expire_date` datetime DEFAULT NULL COMMENT '만료날짜 ',
    `update_date` datetime DEFAULT NULL COMMENT '갱신날짜 ',
    PRIMARY KEY (`id`),
    KEY `address` (`address`),
    KEY `endpoint` (`endpoint`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='주소별 push 정보';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_ADDRESS_REPORT 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_ADDRESS_REPORT` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `reported_addr` varchar(66) CHARACTER SET latin1 DEFAULT NULL COMMENT '신고 대상 주소',
    `reporter_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT '신고자 주소',
    `report_type` tinyint(2) DEFAULT NULL COMMENT '신고 종류',
    `image_file` varchar(50) DEFAULT NULL COMMENT '신고 업로드 파일',
    `ref_url` varchar(256) DEFAULT NULL COMMENT '신고 참조 url',
    `create_date` datetime DEFAULT NULL COMMENT '신고날짜',
    PRIMARY KEY (`id`),
    UNIQUE KEY `reported_addr_reporter_addr` (`reported_addr`,`reporter_addr`),
    KEY `reporter_addr` (`reporter_addr`),
    KEY `reported_addr` (`reported_addr`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='address 신고 내역';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_ADDRESS_TOTAL 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_ADDRESS_TOTAL` (
    `address` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'address   / 주소',
    `balance` varchar(32) DEFAULT NULL COMMENT 'balance  / 잔액',
    `tx_count` int(11) unsigned DEFAULT NULL COMMENT 'transaction count  / 트랜잭션 개수',
    `node_type` varchar(10) DEFAULT NULL COMMENT 'node type   /  노드 타입',
    `is_update` tinyint(1) DEFAULT '0' COMMENT '1 블록 처리 시 업데이트 여부',
    `balance_order` decimal(64,18) DEFAULT NULL COMMENT '잔액순 정렬용',
    `reported_count` int(11) unsigned DEFAULT '0' COMMENT '신고된 횟수',
    `report_count` int(11) unsigned DEFAULT '0' COMMENT '신고한 횟수',
    PRIMARY KEY (`address`),
    KEY `balance_order` (`balance_order`),
    KEY `node_type` (`node_type`),
    KEY `is_update` (`is_update`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Total user address / 모든 유저 주소 정보 ';
-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 icon_mainnet.T_BLOCK 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_BLOCK` (
    `hash` char(66) CHARACTER SET latin1 DEFAULT NULL COMMENT '블록 해시',
    `height` int(11) unsigned NOT NULL COMMENT '블록 높이',
    `create_date` datetime DEFAULT NULL COMMENT '생성 시간',
    `tx_count` int(11) unsigned DEFAULT NULL COMMENT 'tx 개수',
    `fee` varchar(30) DEFAULT NULL COMMENT '수수료',
    `amount` varchar(30) DEFAULT NULL COMMENT '송금액',
    PRIMARY KEY (`height`),
    UNIQUE KEY `hash` (`hash`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Recenty Block / 최근 블록 정보 ';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_BLOCK_TOTAL 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_BLOCK_TOTAL` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `hash` char(66) CHARACTER SET latin1 DEFAULT NULL COMMENT '블록 해시',
    `height` int(11) unsigned NOT NULL COMMENT '블록 높이',
    `create_date` datetime DEFAULT NULL COMMENT '생성 시간',
    `tx_count` int(11) DEFAULT NULL COMMENT 'tx 개수',
    `prev_hash` char(66) CHARACTER SET latin1 DEFAULT NULL COMMENT '이전 해시',
    `block_size` int(11) DEFAULT NULL COMMENT '블록사이즈',
    `fee` varchar(30) DEFAULT NULL COMMENT '세금',
    `amount` varchar(30) DEFAULT NULL COMMENT '금액',
    PRIMARY KEY (`id`),
    KEY `height` (`height`),
    KEY `hash` (`hash`),
    KEY `create_date` (`create_date`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='total  Block / 모든 블록 정보 ';


-- 테이블 icon_mainnet.T_CONTRACT 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_CONTRACT` (
    `contract_addr` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'contract address /  컨트렉트 주소',
    `version` int(11) DEFAULT NULL,
    `name` varchar(256) CHARACTER SET utf8mb4 DEFAULT NULL,
    `irc_version` varchar(10) DEFAULT NULL,
    `symbol` varchar(8) DEFAULT NULL COMMENT 'token symbol / 토큰 심볼 ',
    `holder_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'creator address / 생성자 주소 ',
    `holder_count` int(11) unsigned DEFAULT NULL COMMENT 'token holder count / 토큰 홀더 숫자',
    `transfer_count` int(11) unsigned DEFAULT NULL COMMENT 'token transfer count / 토큰 거래 횟수',
    `total_supply` varchar(32) DEFAULT NULL COMMENT 'total_supply / 총 발행량 ',
    `decimals` int(11) DEFAULT NULL COMMENT 'decimals / 자릿수',
    `state` tinyint(2) DEFAULT NULL COMMENT '0: 대기 1: 성공 2: 실패',
    `active_sort` tinyint(1) DEFAULT '1' COMMENT '0: accept 1:reject/pending',
    `name_sort` tinyint(1) DEFAULT '1' COMMENT '0: 이름 O, 1:이름 x',
    PRIMARY KEY (`contract_addr`),
    KEY `name` (`name`),
    KEY `irc_version` (`irc_version`,`name`),
    KEY `active_sort_name_sort` (`active_sort`,`name_sort`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_CONTRACT_HISTORY 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_CONTRACT_HISTORY` (
    `contract_addr` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'contract address / 컨트렉트 주소 ',
    `version` int(11) NOT NULL COMMENT 'contract version / 컨트렉트 버젼 : v2  v3 ',
    `compiler` varchar(10) DEFAULT NULL COMMENT 'compier version( to_do  ) /  컴파일러 버젼 (추후 개발 예정 )',
    `create_tx` char(66) CHARACTER SET latin1 DEFAULT NULL COMMENT 'deploy transaction / 생성/업데이트 트랜잭션 ',
    `create_date` datetime DEFAULT NULL COMMENT 'deploy date / 생성 요청 시간 ',
    `verified_tx` char(66) CHARACTER SET latin1 DEFAULT NULL COMMENT 'score verified transaction hash / 검증 tx hash ',
    `verified_date` datetime DEFAULT NULL COMMENT 'score verified date / 검증 날짜 ',
    `creator` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'score creator address /  스코어 생성자 주소 ',
    `state` tinyint(2) DEFAULT NULL COMMENT 'score status  (0 : pendding / 1: accept / 2: rejected   ) / 스코어 상태값  (0 : pendding / 1: accept / 2: rejected   )',
    PRIMARY KEY (`contract_addr`,`version`),
    KEY `create_tx` (`create_tx`),
    KEY `state` (`state`),
    KEY `create_date` (`create_date`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='contract history info //  컨트랙트  이력 정보 ';

-- 내보낼 데이터가 선택되어 있지 않습니다.

CREATE TABLE IF NOT EXISTS `T_FEE_DETAILS` (
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL,
    `step_used_details` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`tx_hash`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='fee 2.0의 used details 상세정보 저장.\r\n없는 경우 insert 하지 않음.';

CREATE TABLE IF NOT EXISTS `T_INTERNAL_TX` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `parent_tx_hash` char(66) CHARACTER SET latin1 NOT NULL COMMENT 'parent transaction hash / 부모 트랜잭션 해시 ',
    `tx_index` int(11) unsigned NOT NULL COMMENT 'index / 순서 ',
    `contract_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'contract address / 컨트랙트 주소 ',
    `height` int(11) unsigned DEFAULT NULL COMMENT 'block height / 블록 높이',
    `create_date` datetime DEFAULT NULL COMMENT 'create date / 생성 날짜 ',
    `from_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'sender address / 보내는 사람 주소 ',
    `to_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'receive address / 수신자 주소 ',
    `amount` varchar(30) DEFAULT NULL COMMENT 'icx amount / icx 금액 ',
    PRIMARY KEY (`id`),
    KEY `parent_tx_hash` (`parent_tx_hash`),
    KEY `contract_addr` (`contract_addr`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_INTERNAL_TX_VIEW 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_INTERNAL_TX_VIEW` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `parent_tx_hash` char(66) CHARACTER SET latin1 NOT NULL COMMENT 'parent transaction hash / 부모 트랜잭션 해시 ',
    `tx_index` int(11) unsigned NOT NULL COMMENT 'index / 순서 ',
    `address` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'address / 보내거나 받은 사람 주소',
    `address_type` tinyint(1) DEFAULT NULL COMMENT '0:from, 1:to',
    PRIMARY KEY (`id`),
    KEY `address` (`address`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='contract_addr,create_date,height,state 컬럼 삭제 필요';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_MAIN_BLOCK 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_MAIN_BLOCK` (
    `block_height` int(11) unsigned NOT NULL COMMENT 'block height / 블록높이  ',
    `hash` char(66) CHARACTER SET latin1 DEFAULT NULL COMMENT 'block hash / 블록 해시',
    `tx_count` int(11) unsigned DEFAULT NULL COMMENT 'tx count  by block / 트랜잭션 개수',
    `create_date` datetime DEFAULT NULL COMMENT 'create date / 생성일',
    PRIMARY KEY (`block_height`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='main page block list /  메인 페이지 블록 리스트 ';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_MAIN_CHART 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_MAIN_CHART` (
                                              `target_date` date NOT NULL COMMENT 'day / 날짜',
                                              `tx_count` int(11) DEFAULT NULL COMMENT 'tx count / tx 개수',
    PRIMARY KEY (`target_date`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Main page dairy chart / 메인 페이지 차트 ';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_MAIN_INFO 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_MAIN_INFO` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `transaction_count` int(11) unsigned DEFAULT NULL COMMENT 'total icon tx count / 총 트랜잭션 개수',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='main page info / 메인 페이지 정보 ';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_MAIN_TX 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_MAIN_TX` (
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL COMMENT '트랜잭션 해쉬',
    `amount` varchar(30) DEFAULT NULL COMMENT '금액',
    `fee` varchar(30) DEFAULT NULL COMMENT '세금',
    `score_yn` varchar(1) DEFAULT NULL COMMENT 'score 여부',
    `create_date` datetime DEFAULT NULL COMMENT '생성일',
    `state` tinyint(1) DEFAULT NULL COMMENT '0:실패, 1:성공',
    PRIMARY KEY (`tx_hash`),
    KEY `create_date` (`create_date`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 내보낼 데이터가 선택되어 있지 않습니다

-- 테이블 icon_mainnet.T_TOKEN_ADDRESS 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_TOKEN_ADDRESS` (
    `address` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'address / 유저 주소 ',
    `contract_addr` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'token contract address  / 컨트렉트 주소  ',
    `tx_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'token transaction  count  / 토큰 트랜잭션 카운터 ',
    `quantity` varchar(100) DEFAULT '0' COMMENT 'token quantity / 토큰 수량 ',
    `quantity_order` decimal(64,18) DEFAULT NULL COMMENT '정렬용',
    PRIMARY KEY (`address`,`contract_addr`),
    KEY `contract_addr_quantity_order` (`contract_addr`,`quantity_order`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='token holder info / 토큰 소유자 정보 ';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_TOKEN_TX 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_TOKEN_TX` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `contract_addr` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'contract address / 컨트렉트 어드래스 ',
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL COMMENT 'transaction hash / 트랜잭션 해시 ',
    `tx_index` int(11) unsigned NOT NULL COMMENT 'index / 순서 ',
    `block_height` int(11) unsigned NOT NULL COMMENT 'tokenAddress 갱신대상 조회용',
    `age` datetime DEFAULT NULL COMMENT 'create time / 생성 날짜 ',
    `from_addr` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'sender address / 송금자 주소 ',
    `to_addr` char(42) CHARACTER SET latin1 NOT NULL COMMENT 'recieve address / 수신자 주소 ',
    `quantity` varchar(100) NOT NULL COMMENT 'token  quantity / 토큰 수량 ',
    `irc_version` varchar(8) DEFAULT NULL COMMENT 'irc version / irc 버젼 ',
    `fee` varchar(30) DEFAULT NULL COMMENT 'fee / fee ',
    PRIMARY KEY (`id`),
    UNIQUE KEY `tx_hash_tx_index` (`tx_hash`,`tx_index`),
    KEY `tx_hash` (`tx_hash`),
    KEY `block_height` (`block_height`),
    KEY `contract_addr` (`contract_addr`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='token transaction / 토큰 트랜잭션 ';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_TRANSACTION 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_TRANSACTION` (
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL COMMENT 'transaction hash : 트랜잭션 해시',
    `height` bigint(20) unsigned DEFAULT NULL COMMENT 'block height / 블록 높이',
    `create_date` datetime DEFAULT NULL COMMENT 'create date / 생성 시간 ',
    `from_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'sender address / /송금자 주소',
    `to_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'recieve / 수금자 주소',
    `fee` varchar(30) DEFAULT NULL COMMENT 'fee / fee ',
    `amount` varchar(30) DEFAULT NULL COMMENT 'icx amount / 금액',
    `state` tinyint(1) DEFAULT NULL COMMENT 'tx status / 트랜잭션 status  0: fail 1: success ',
    `tx_type` tinyint(2) DEFAULT '0' COMMENT 'tx_type / 트랜잭션 타입   0: icx send 1: token send  2:contact  call  3 : contract install 4 : contract update 5 : contact reject ',
    `contract_addr` char(42) DEFAULT NULL COMMENT 'contract address / 컨트렉트 주소',
    PRIMARY KEY (`tx_hash`),
    KEY `height_create_date` (`height`,`create_date`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Transaction / 트랜잭션(페이징용) ';


-- 테이블 icon_mainnet.T_TOKEN_TX_VIEW 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_TOKEN_TX_VIEW` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `contract_addr` char(42) CHARACTER SET latin1 NOT NULL,
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL,
    `tx_index` int(11) unsigned NOT NULL,
    `block_height` int(11) unsigned NOT NULL COMMENT 'tokenAddress 갱신대상 조회용',
    `address` char(42) CHARACTER SET latin1 NOT NULL,
    `address_type` tinyint(1) DEFAULT NULL COMMENT '0:from, 1:to',
    PRIMARY KEY (`id`),
    KEY `address` (`address`),
    KEY `block_height` (`block_height`),
    KEY `contract_addr_address` (`contract_addr`,`address`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='토큰 트랜잭션 주소별 검색용 해시 뷰';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 icon_mainnet.T_TRANSACTION_TOTAL 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_TRANSACTION_TOTAL` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `height` int(11) unsigned NOT NULL COMMENT '블록 높이',
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL COMMENT '트랜잭션 해시',
    `hash` char(66) CHARACTER SET latin1 DEFAULT NULL COMMENT '블록 해시',
    `create_date` datetime DEFAULT NULL COMMENT '생성시간 UTC기준',
    `from_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT '송금자 주소',
    `to_addr` char(42) CHARACTER SET latin1 DEFAULT NULL COMMENT '수금자 주소',
    `fee` varchar(30) DEFAULT NULL COMMENT '세금',
    `amount` varchar(30) DEFAULT NULL COMMENT '금액',
    `state` tinyint(1) DEFAULT NULL COMMENT '0:실패, 1:성공',
    `tx_type` tinyint(2) DEFAULT '0' COMMENT '0:ico, 1:token, 2:call, 3:install, 4:update',
    `step_limit` varchar(32) DEFAULT NULL,
    `step_used_tx` varchar(32) DEFAULT NULL,
    `step_price` varchar(32) DEFAULT NULL,
    `data_type` varchar(10) DEFAULT NULL,
    `version` varchar(5) DEFAULT NULL,
    `error_code` varchar(10) DEFAULT NULL,
    `error_msg` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
    `internal_tx_count` int(11) unsigned DEFAULT '0',
    `contract_addr` varchar(42) CHARACTER SET latin1 DEFAULT NULL COMMENT 'contract address / 컨트렉트 주소',
    `reported_count` int(11) unsigned DEFAULT '0' COMMENT '신고된 횟수',
    `btp_header_block_height` int(11) unsigned NULL,
    `btp_header_network_id` char(11) CHARACTER SET latin1 NULL,
    `btp_message_sn` int(11) unsigned NULL,
    PRIMARY KEY (`id`),
    KEY `height` (`height`),
    KEY `tx_hash` (`tx_hash`),
    KEY `contract_addr_create_date` (`contract_addr`,`create_date`),
    KEY `create_date` (`create_date`),
    KEY `btp_header` (`btp_header_block_height` , `btp_header_network_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_TRANSACTION_VIEW 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_TRANSACTION_VIEW` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL,
    `height` int(11) unsigned NOT NULL COMMENT 'txCount업데이트 조회용',
    `address` char(66) CHARACTER SET latin1 NOT NULL,
    `address_type` tinyint(2) DEFAULT NULL COMMENT '0:from, 1:to, 2:self',
    `tx_type` tinyint(2) DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY `address` (`address`),
    KEY `height` (`height`),
    KEY `tx_type_address` (`tx_type`,`address`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_TX_DATA 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_TX_DATA` (
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL,
    `data_body` mediumtext CHARACTER SET utf8mb4,
    `is_safe` tinyint(1) DEFAULT '1',
    PRIMARY KEY (`tx_hash`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Transaction data / 트랜잭션 데이터 ';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 icon_mainnet.T_TX_RESULT_LOG 구조 내보내기
CREATE TABLE IF NOT EXISTS `T_TX_RESULT_LOG` (
    `e_index` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `tx_hash` char(66) CHARACTER SET latin1 NOT NULL,
    `tx_index` int(11) unsigned NOT NULL DEFAULT '0',
    `contract_addr` char(66) CHARACTER SET latin1 DEFAULT NULL,
    `height` int(11) DEFAULT NULL,
    `age` datetime DEFAULT NULL,
    `method` varchar(150) DEFAULT NULL,
    `event_log` mediumtext CHARACTER SET utf8mb4,
    PRIMARY KEY (`e_index`),
    KEY `contract_addr` (`contract_addr`),
    KEY `tx_hash` (`tx_hash`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `T_CHAIN_INFO` (
    `chain_name` varchar(100) CHARACTER SET latin1 NOT NULL COMMENT 'chain name : 체인 이름',
    `host` varchar(100) CHARACTER SET latin1 NOT NULL COMMENT 'chain host : 체인 호스트 주소',
    `api` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT 'api path',
    `title` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT 'title : 화면에 출력될 화면',
    `channel` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT 'channel : 체널',
    `version` char(5) CHARACTER SET latin1 DEFAULT NULL COMMENT 'chain version',
    PRIMARY KEY (`chain_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `T_CHAIN_STATE` (
    `chain_name` varchar(100) CHARACTER SET latin1 NOT NULL COMMENT 'chain name : 체인 이름',
    `block_height` int(11) DEFAULT NULL,
    PRIMARY KEY (`chain_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `T_BTP_NETWORK` (
    `network_id` char(11) CHARACTER SET latin1 NOT NULL,
    `network_type_id` char(11) CHARACTER SET latin1 NOT NULL,
    `network_name` char(66) CHARACTER SET latin1 NOT NULL,
    `network_type_name` char(66) CHARACTER SET latin1 NOT NULL,
    `url` char(66) CHARACTER SET latin1 NOT NULL,
    `start_height` int(11) DEFAULT NULL,
    PRIMARY KEY (`network_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `T_BTP_HEADER` (
    `block_height` int(11) unsigned NOT NULL,
    `network_id` char(11) CHARACTER SET latin1 NOT NULL,
    `update_number` char(11) CHARACTER SET latin1 NOT NULL,
    `prev` char(66) CHARACTER SET latin1 NOT NULL,
    `message_cnt` int(11) DEFAULT NULL,
    `message_root` char(66) CHARACTER SET latin1 NOT NULL,
    `create_date` datetime DEFAULT NULL,
    `btp_network_network_id` char(11) CHARACTER SET latin1 NOT NULL,
    PRIMARY KEY (`block_height`, `network_id`),
    INDEX `fk_T_BTP_HEADER_T_BTP_NETWORK1_idx` (`btp_network_network_id` ASC),
    CONSTRAINT `fk_T_BTP_HEADER_T_BTP_NETWORK1`
    FOREIGN KEY (`btp_network_network_id`)
    REFERENCES `T_BTP_NETWORK` (`network_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;





-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
