package com.newland.otaupdate.tool;
/************************************************************************
 * 
 * module 			: main
 * file name 		: NDK.java 
 * Author 			: zhengxq
 * version 			: 
 * DATE 			: 
 * directory 		: 
 * description 		: 错误码定�?
 * related document : 
 * 
 ************************************************************************ 
 * log : Revision no message(created for Android platform)
 ************************************************************************/
public interface NDK 
{
	// Android端定义的错误返回�?
		public final int ANDROID_NOT_OPRN_DEV = -2;/**串口设备没有打开**/
		public final int ANDROID_OK = 0;/**成功**/
		public final int ANDROID_PORT_PARA_REE = -2;/**设置串口参数出错**/
		public final int ANDROID_BLOCK_FAIL=-3;/**设置阻塞失败**/
		public final int ANDROID_FD_ERR1=-12;/**fd�?**/
		public final int ANDROID_FD_ERR2=-13;/**fd�?**/
		public final int ANDROID_PARA_ERR=-14;/**参数�?**/
		public final int ANDROID_FD_NOT_VALID=-15;/**fd不支�?**/
		public final int ANDROID_PORT_READ_FAIL=-1;/**读失�?**/
		public final int ANDROID_PORT_FD_ERR=-2;/**fd出错**/
		
		public final int PAYMENT_PORT_BE_OCCUPY = -201;/**被本应用中另外一个支付服务抢�?**/
		public final int PAYMENT_PORT_BE_OTHER_OCCUPY = -202;/**被其他应用中的支付服务抢�?**/
		
		public final int MODEM_CONNECT_AFTERPREDIAL =2;/**已连�?**/
		public final int MODEM_OK_AFTERPREDIAL = 1;/**拨号成功**/
		public final int MODEM_NORETURN_AFTERPREDIAL = 0;
		public final int MODEM_NOPREDIAL = -11;/**并未拨号**/
		public final int MODEM_MS_NODIALTONE = -2;/**没有�?测到拨号�?**/
		public final int MODEM_MS_NOCARRIER =-3;/**没有接受到载�?**/
		public final int MODEM_MS_BUSY = -4;/**�?测到忙音或电话线没接**/
		public final int MODEM_MS_ERROR = -5;/**命令行出�?**/
		public final int MODEM_EXCEPTION_THROW = -111;/**modem异常抛出**/
		
		
	    /**无线*/
	    public final int NDK_ERR_SIM_UNKnow = -4001;
	    public final int NDK_ERR_SIM_NO_USE = -4002;
	    public final int NDK_ERR_SIM_LOCK_USE_PIN = -4003;
	    public final int NDK_ERR_SIM_LOCK_USE_PUK = -4004;
	    public final int NDK_ERR_SIM_LOCK_NET_PIN = -4005;
	    
	    
	    /**print*/
	    public final int NDK_PRINT_FAILED = -5001;					/**打印失败**/
	    //!font命令 找不到ttf文件返回�?
	    public final int NDK_PRINT_FILE_NOT_FIND=-7;
	    public final int NDK_PRINT_PARA_ERR=-6;
	   
	    /**BT*/
	    public final int NDK_BT_NO_SUPPORT = -6001;
	    public final int NDK_BT_DETECT_FAILED = -6002;
	    public final int NDK_BT_CONNECT_FAILED = -6003;
	    
	    /**pinpad*/
	    public final int NDK_PININPUT_FAILED = -7001;
	    
	    /** apk安装验签*/
	    public final int PACKAGE_INSTALL_SUCCESS  =  0;							/**apk安装成功成功响应�?**/
	    public final int  ERROR_PACKAGE_INSTALL_FAILED  = -100;					/**默认失败**/
	    public final int ERROR_PACKAGE_INSTALL_FAILED_INVALID_APK    = -101;  	/**非法的apk类型**/
	    public final int ERROR_PACKAGE_INSTALL_FAILED_PERMISSION_FAILED = -102;	/**安装权限不足**/
	    public final int  ERROR_PACKAGE_INSTALL_FAILED_NO_SPACE = -4;			/**空间不足**/
	    public final int  ERROR_PACKAGE_INSTALL_FAILED_SIGNATURE_FAILED = -104;	/**签名信息错误**/
	    public final int  ERROR_PACKAGE_INSTALL_FAILED_SIGNATURE_FAILED2 = -108;	/**美团签名信息错误**/
	    public final int ERROR_PACKAGE_INSTALL_VERSION_DOWNGRADE = -107;		/**已安装了更高版本的同名数据包**/
	    public final int PACKAGE_INSTALL_CLOUDVERIFY_SUCCESS = 100;				/**拉卡拉云授权成功**/
	    public final int ERROR_PACKAGE_INSTALL_FAILED_CLOUDVERIFY_FAILED = -105;/**拉卡拉云授权失败**/
	    public final int ERROR_PACKAGE_INSTALL_FAILED_CLOUDVERIFY_WITHOUT_NEWWORK = -106;/**拉卡拉云授权失败网络不可�?**/
	    public final int ERROR_PACKAGE_INSTALL_FAILED_MINS_SIGNATURE_FAILED = -110;  /**白名单验证失�?**/
	    public final int ERROR_PACKAGE_INSTALL_NO_MANAGE_NEWLAND = -8;				/**无MANAGE_NEWLAND权限**/
	   
	    public final int  PACKAGE_DELETE_SUCCESS  =  0;							/**卸载成功响应�?**/
	    public final int  ERROR_PACKAGE_DELETE_FAILED  = -200;					/**默认失败**/
	    public final int  ERROR_PACKAGE_DELETE_FAILED_APP_NOT_FOUND  = -201;	/**默认失败，卸载app找不�?**/
	    public final int  ERROR_PACKAGE_DELETE_FAILED_APP_WHITE_UNINSTALL  = -203;	/**存在于卸载白名单�?**/
	    public final int  ERROR_PACKAGE_DELETE_FAILED_NO_PERMISSION  = -1;	/**无法卸载返回-1**/
	    
	    /** 扫码*/
	    public final int NDK_SCAN_NO_RESULT = -601; /**扫码无结果为null**/
	    public final int NDK_SCAN_DATA_ERR = -602; /**扫码数据错误**/
	    public final int NDK_SCAN_FAULT = -603;   /**扫码失败**/
	    public final int NDK_SCAN_OK = 0;         /**扫码成功**/
	    public final int NDK_SCAN_COTINUE_NULL = -605;  /**连续扫码返回值为null**/
	    public final int NDK_SCAN_NO_RELEASE = -2;  /**回调接口注册未释放，初始化失�?**/
	    public final int NDK_SCAN_PARSE_LIBRARY_FAIL = -1;  /**解析库初始化异常**/
	    public final int NDK_SCAN_PARSE_FAIL = 0;  /**无法解析，图片有损坏**/
	    public final int NDK_SCAN_PARSE_SUCC = 1;  /**转换数据成功，正在解析中**/
	    
	    /** 指令集错误返回码说明*/
	    public final int SDK_OK = 0;/**成功*/
	    public final int SDK_ERR = 2;/**错误*/
	    public final int SDK_ERR_INVOKE_FAILED = 6;/**指令集执行失�?*/
	    public final int SDK_ERR_TIMEOUT = 7;/**指令集执行超�?*/
	    public final int SDK_ERR_SECP_KCV_CHK=41;/**密钥校验值错�?*/
	    public final int SDK_ERR_SECP_ERR =  42;/**错误*/
	    public final int SDK_ERR_SECP_GET_KEY = 43;/**密钥索引无效*/
	    public final int SDK_ERR_SECP_LEN_ERR = 45;/**主密钥数据长度错�?*/
	    public final int SDK_ERR_SECP_TR31_ERR = 46;/**无效的TR31格式*/
	    public final int SDK_ERR_SECCR_GET_KEY = 47;/**读密钥记录错�?*/
	    
	    /**nfc模块的错误返回码*/
	    public final int NFC_NO_APDU = -10;
	    
		/** 蓝牙错误返回�?*/
		public final int BT_OK = 0;/**蓝牙底座操作成功**/
		public final int BT_CONNECT_FAILED = -301;/**蓝牙链路建立失败**/
		public final int BT_WRITE_FAILED = -302;/**发�?�数据失�?**/
		public final int BT_READ_FAILED = -303;/**接收数据失败**/
		public final int BT_COMPARE_FAILED = -304;/**比较数据失败**/
		
		/** Android端文件系统的返回�? add by zhengxq 20171115*/
		public final int JDK_OK = 0;
		public final int JDK_PARA_ERR = -6;
		public final int JDK_FS_OPEN_FAIL = -11;
		public final int JDK_FS_CREATE_FAIL = -12;
		public final int JDK_FS_PATH_ERR = -13;
		public final int JDK_FS_NO_EXIST = -14;
		public final int JDK_FS_READ_FAIL = -15;/**读文件操作失�?*/
		public final int JDK_FS_DEL_FAIL = -16;/**删除文件失败*/
		public final int JDK_FS_CLOSE_FAIL = -17;/**关闭文件失败*/
		public final int JDK_FS_SIZE_FAIL = -18;/**获取文件大小失败*/
		
		public static final int DRIVER_NOT_FOUND = 1000;
		
		public static final int INIT_DRIVER_FAIED = 1001;
		
		public static final int CONNECT_DEVICE_FAILED = 1002;
		
		public static final int GET_TRACKTEXT_FAILED = 1003;
		
		public static final int PRINT_FAILED = 1004;
		
		public static final int ICCARD_FAILED = 1005;
		
		public static final int RFCARD_FAILED = 1006;
		
		public static final int KEYCODE_FAILED = 1007;
		
		/** 串口3异常 */
		public static final int UART3_FAILED = 1008;
		
		/** USB接口异常 */
		public static final int USB_FAILED = 1009;
		
		/** TF卡异�? */
		public static final int TFCARD_FAILED = 1010;
		
		/** WIFI异常 */
		public static final int WIFI_FAILED = 1011;
		
		/** 以太网异�? */
		public static final int ETHERNET_FAILED = 1012;
		
		/** 3G异常 */
		public static final int MOBILE_FAILED = 1013;
		
		/** RTC异常 */
		public static final int RTC_FAILED = 1014;
		
		/** 设备模块�?测异�? */
		public static final int DEVICE_MODULE_DETECT_EXCEPTION = 1015;
		
		/**wifi探针错误码定�?*/
		public static final int WIFI_SUCC = 0;/**成功*/
		public static final int WIFI_INPUT_SAME = 1;/**输入的参数与底层的�?�原本就�?�?*/
		public static final int WIFI_OPEN_FILE_FAIL = -2;/**打开文件失败*/
		public static final int WIFI_SEEK_FAIL = -3;/**fseek失败*/
		public static final int WIFI_PARA_ERR = -4;/**参数异常*/
		public static final int WIFI_GLO_CLAOSE = -5;/**参数异常*/
		
		/**RS232串口超时*/
		public static final int RS232_TIMEOUT = 1016;
		
		/**NDK.h文件的各种错误返回�??*/
		public final  int NDK_OK1=1;					/**<补丁包下载成�?*/
		public final  int NDK_OK=0;					/**<操作成功*/
		public final int NDK_ERR=-1;				/**<操作失败*/
		public final int NDK_ERR_INIT_CONFIG = -2;	 /**<初始化配置失�?*/
		public final int NDK_ERR_CREAT_WIDGET = -3;	/**<创建界面错误*/
		public final int NDK_ERR_OPEN_DEV = -4;/**<打开设备文件错误*/
		public final int NDK_ERR_IOCTL = -5;	/**<驱动调用错误*/
		public final int NDK_ERR_PARA = -6;	/**<参数非法*/
		public final int NDK_ERR_PATH = -7;	/**<文件路径非法*/
		public final int NDK_ERR_DECODE_IMAGE = -8;	/**<图像解码失败*/
		public final int NDK_ERR_MACLLOC=-9;			/*内存空间不足*/
		public final int NDK_ERR_TIMEOUT = -10;		/**<超时错误*/
		public final int NDK_ERR_QUIT = -11;		/**<按取消�??�?*/
		public final int NDK_ERR_WRITE = -12; /**<写文件失�?*/
		public final int NDK_ERR_READ = -13; 	/**<读文件失�?*/
		public final int NDK_ERR_OVERFLOW = -15;		/**<缓冲溢出*/
		public final int NDK_ERR_SHM = -16;	/**<共享内存出错*/
		public final int NDK_ERR_NO_DEVICES=-17;	/**<POS无该设备*/
		public final int NDK_ERR_NOT_SUPPORT=-18; /**<不支持该功能*/
		public final int NDK_ERR_NO_SNK = -19;           /**<不存在SNK*/ //add by yanb,20170707 for UNIONPAY REQUIREMENT
		public final int NDK_ERR_NO_TUSN = -20;          /**<不存在TUSN*/ //add by yanb,20170707 for UNIONPAY REQUIREMENT
		public final int NDK_ERR_NOSWIPED = -50;	/**<无磁卡刷卡记�?*/
		public final int NDK_ERR_SWIPED_DATA=-51;	/**<驱动磁卡数据格式�?*/
		public final int NDK_ERR_USB_LINE_UNCONNECT = -100;  /**<USB线未连接*/
		public final int NDK_ERR_NO_SIMCARD = -201;	/**<无SIM�?*/
		public final int NDK_ERR_PIN = -202; /**<SIM卡密码错�?*/
		public final int NDK_ERR_PIN_LOCKED = -203;	/**<SIM卡被锁定*/
		public final int NDK_ERR_PIN_UNDEFINE = -204;	/**<SIM卡未定义错误*/
		public final int NDK_ERR_EMPTY = -205;			/**<返回空串*/
		public final int NDK_ERR_ETH_PULLOUT = -250;		/**<以太网未插线*/
		public final int NDK_ERR_PPP_PARAM = -301;	/**<PPP参数出错*/
		public final int NDK_ERR_PPP_DEVICE = -302;/**<PPP无效设备*/
		public final int NDK_ERR_PPP_OPEN = -303; /**<PPP已打�?*/
		public final  int NDK_ERR_TCP_ALLOC = -304;	/**<无法分配*/
		public final int NDK_ERR_TCP_PARAM = -305;	/**<无效参数*/
		public final int NDK_ERR_TCP_TIMEOUT = -306;	/**<传输超时*/
		public final int NDK_ERR_TCP_INVADDR = -307;	/**<无效地址*/
		public final int NDK_ERR_TCP_CONNECT = -308;	/**<没有连接*/
		public final int NDK_ERR_TCP_PROTOCOL = -309;/**<协议错误*/
		public final int NDK_ERR_TCP_NETWORK = -310;	/**<网络错误*/
		public final int NDK_ERR_TCP_SEND = -311;	/**<发�?�错�?*/
		public final int NDK_ERR_TCP_RECV = -312;	/**<接收错误*/

		public final int NDK_ERR_WLM_SEND_AT_FAIL = -320;		/**<无线发�?�AT失败*/
		
		public final int NDK_ERR_SSL_PARAM = -350;       /**<无效参数*/
		public final int NDK_ERR_SSL_ALREADCLOSE = -351; /**<连接已关�?*/
		public final int NDK_ERR_SSL_ALLOC = -352;       /**<无法分配*/
		public final int NDK_ERR_SSL_INVADDR = -353;     /**<无效地址*/
		public final int NDK_ERR_SSL_TIMEOUT = -354;     /**<连接超时*/
		public final int NDK_ERR_SSL_MODEUNSUPPORTED = -355; /**<模式不支�?*/
		public final int NDK_ERR_SSL_SEND = -356;        /**<发�?�错�?*/
		public final int NDK_ERR_SSL_RECV = -357;        /**<接收错误*/
		public final int NDK_ERR_SSL_CONNECT = -358;       /**<没有连接*/

		public final int NDK_ERR_GET_NETADDR = -401;/**<获取本地地址或子网掩码失�?*/
		public final int NDK_ERR_GET_GATEWAY = -402;	/**<获取网关地址失败*/
		public final int NDK_ERR_ADDR_ILLEGAL =-403;/**<获取地址格式错误*/
		public final int NDK_ERR_NET_UNKNOWN_COMMTYPE=-404;	/**<未知的�?�信类型*/
		public final int NDK_ERR_NET_INVALIDIPSTR=-405;		/**<无效的IP字符�?*/
		public final int NDK_ERR_NET_UNSUPPORT_COMMTYPE=-406;	/**<不支持的通信类型*/
		
		public final int NDK_ERR_THREAD_PARAM = -450;     /**<无效参数*/
		public final int NDK_ERR_THREAD_ALLOC = -451;     /**<无效分配*/
		public final int NDK_ERR_THREAD_CMDUNSUPPORTED = -452;     /**<命令不支�?*/

		public final int NDK_ERR_MODEM_RESETFAIL = -501;			/**<MODEM 复位失败*/
		public final int NDK_ERR_MODEM_GETSTATUSFAIL = -502;		/**<MODEM 获取状�?�失�?*/
		public final int NDK_ERR_MODEM_SLEPPFAIL = -503;			/**<MODEM 休眠失败*/
		public final int NDK_ERR_MODEM_SDLCINITFAIL = -504;		/**<MODEM 同步初始化失�?*/
		public final int NDK_ERR_MODEM_INIT_NOT=-505;/**<MODEM未进行初始化*/
		public final int NDK_ERR_MODEM_SDLCWRITEFAIL=-506;/**<MODEM同步写失�?*/
		public final int NDK_ERR_MODEM_ASYNWRITEFAIL = -507;		/**<MODEM 异步写数据失�?*/
		public final int NDK_ERR_MODEM_ASYNDIALFAIL = -508;		/**<MODEM 异步拨号失败*/
		public final int NDK_ERR_MODEM_ASYNINITFAIL = -509;		/**<MODEM 异步初始化失�?*/
		public final int NDK_ERR_MODEM_SDLCHANGUPFAIL=-510;/**<MODEM同步挂断失败*/
		public final int NDK_ERR_MODEM_ASYNHANGUPFAIL=-511;/**<MODEM异步挂断失败*/
		public final int NDK_ERR_MODEM_SDLCCLRBUFFAIL=-512;/**<MODEM同步清缓冲失�?*/
		public final int NDK_ERR_MODEM_ASYNCLRBUFFAIL=-513;/**<MODEM异步清缓冲失�?*/
		public final int NDK_ERR_MODEM_ATCOMNORESPONSE=-514;/**<MODEM AT命令无响�?*/
		public final int NDK_ERR_MODEM_PORTWRITEFAIL=-515;/**<MODEM 端口写数据失�?*/
		public final int NDK_ERR_MODEM_SETCHIPFAIL=-516;/**<MODEM 模块寄存器设置失�?*/
		public final int NDK_ERR_MODEM_STARTSDLCTASK=-517;/**<MODEM 拨号时开启SDLC 任务失败*/
		public final int NDK_ERR_MODEM_GETBUFFLENFAIL = -518;	/**<MODEM 获取数据长度失败*/
		public final int NDK_ERR_MODEM_QUIT=-519;/**<MODEM 手动�?�?*/
		public final int NDK_ERR_MODEM_NOPREDIAL=-520;/**<MODEM 未拨�?*/
		public final int NDK_ERR_MODEM_NOCARRIER=-521;/**<MODEM 没载�?*/
		public final int NDK_ERR_MODEM_NOLINE=-523;/**未插�?**/
		public final int NDK_ERR_MODEM_OTHERMACHINE=-524;/**存在并机**/
		public final int NDK_ERR_MODEM_PORTREADFAIL=-525;/**<MODEM 端口读数据失�?*/
		public final int NDK_ERR_MODEM_CLRBUFFAIL=-526;/**<MODEM 清空缓冲失败*/
		public final int NDK_ERR_MODEM_ATCOMMANDERR=-527;/**<MODEM AT命令错误*/
		public final int NDK_ERR_MODEM_STATUSUNDEFINE=-528;/**<MODEM状�?�未确认状�??*/
		public final int NDK_ERR_MODEM_GETVERFAIL=-529;			/**<MODEM获取版本失败*/
		public final int NDK_ERR_MODEM_SDLCDIALFAIL = -530;		/**<MODEM 同步拨号失败*/
		public final int NDK_ERR_MODEM_SELFADAPTFAIL = -530;     /**<MODEM自�?�应失败*/
		
		public final int NDK_ERR_ICC_WRITE_ERR =			-601;	/**< 写器�?83c26出错*/
		public final int NDK_ERR_ICC_COPYERR=			-602;	/**<内核数据拷贝出错*/
		public final int NDK_ERR_ICC_POWERON_ERR=		-603;	/**<上电出错*/
		public final int NDK_ERR_ICC_COM_ERR=			-604;	/**<命令出错*/
		public final int NDK_ERR_ICC_CARDPULL_ERR=		-605;	/**<卡拔出了*/
		public final int NDK_ERR_ICC_CARDNOREADY_ERR=	-606;	/**<卡未准备�?*/

		public final int NDK_ERR_USDDISK_PARAM =  -650;          /**<无效参数*/
		public final int NDK_ERR_USDDISK_DRIVELOADFAIL =  -651;  /**<U盘或SD卡驱动加载失�?*/
		public final int NDK_ERR_USDDISK_NONSUPPORTTYPE =  -652; /**<不支持的类型*/
		public final int NDK_ERR_USDDISK_UNMOUNTFAIL =  -653;    /**<挂载失败*/
		public final int NDK_ERR_USDDISK_UNLOADDRIFAIL =  -654;  /**<卸载驱动失败*/
		public final int NDK_ERR_USDDISK_IOCFAIL =  -655;        /**<驱动调用错误*/

		public final int NDK_ERR_APP_BASE=(-800);							/**<应用接口错误基数*/
		public final int NDK_ERR_APP_NOT_EXIST=(NDK_ERR_APP_BASE-1);			/**<应用项不存在*/
		public final int NDK_ERR_APP_NOT_MATCH=(NDK_ERR_APP_BASE-2);	   /**补丁包文件不匹配**/
		public final int NDK_ERR_APP_FAIL_SEC=(NDK_ERR_APP_BASE-3);	   			/**获取安全攻击状�?�失�?**/
		public final int NDK_ERR_APP_SEC_ATT=(NDK_ERR_APP_BASE-4);	  	 		/**存在安全攻击**/
		public final int NDK_ERR_APP_FILE_EXIST=(NDK_ERR_APP_BASE-5);	  	 		/**应用中该文件已存�?**/
		public final int NDK_ERR_APP_FILE_NOT_EXIST=(NDK_ERR_APP_BASE-6);	  	 		/**应用中该文件不存�?**/
		public final int NDK_ERR_APP_FAIL_AUTH=(NDK_ERR_APP_BASE-7);	  	 		/**证书认证失败**/
		public final int NDK_ERR_APP_LOW_VERSION=(NDK_ERR_APP_BASE-8);	/**<补丁包的版本比应用版本低*/
		
		public final int NDK_ERR_APP_MAX_CHILD=(NDK_ERR_APP_BASE-9);			/**<子应用运行数超过�?大运行数�?*/
		public final int NDK_ERR_APP_CREAT_CHILD=(NDK_ERR_APP_BASE-10);		/**<创建子进程错�?*/
		public final int NDK_ERR_APP_WAIT_CHILD=(NDK_ERR_APP_BASE-11);		/**<等待子进程结束错�?*/
		public final int NDK_ERR_APP_FILE_READ=(NDK_ERR_APP_BASE-12);		/**<读文件错�?*/
		public final int NDK_ERR_APP_FILE_WRITE=(NDK_ERR_APP_BASE-13);		/**<写文件错�?*/
		public final int NDK_ERR_APP_FILE_STAT=(NDK_ERR_APP_BASE-14);		/**<获取文件信息错误*/
		public final int NDK_ERR_APP_FILE_OPEN=(NDK_ERR_APP_BASE-15);		/**<文件打开错误*/
		public final int NDK_ERR_APP_NLD_HEAD_LEN=(NDK_ERR_APP_BASE-16);		/**<NLD文件获取头信息长度错�?*/
		public final int NDK_ERR_APP_PUBKEY_EXPIRED=(NDK_ERR_APP_BASE-17);	/**<公钥有效�?*/
		public final int NDK_ERR_APP_MMAP=(NDK_ERR_APP_BASE-18);				/**<内存映射错误*/
		public final int NDK_ERR_APP_MALLOC=(NDK_ERR_APP_BASE-19);			/**<动�?�内存分配错�?*/
		public final int NDK_ERR_APP_SIGN_DECRYPT=(NDK_ERR_APP_BASE-20);		/**<签名数据解签错误*/
		public final int NDK_ERR_APP_SIGN_CHECK=(NDK_ERR_APP_BASE-21);		/**<签名数据校验错误*/
		public final int NDK_ERR_APP_MUNMAP=(NDK_ERR_APP_BASE-22);			/**<内存映射释放错误*/
		public final int NDK_ERR_APP_TAR=(NDK_ERR_APP_BASE-23);				/**<tar命令执行失败*/
		public final int NDK_ERR_APP_KEY_UPDATE_BAN=(NDK_ERR_APP_BASE-24);				/**<调试状�?�禁止密钥升�?*/
		public final int NDK_ERR_APP_FIRM_PATCH_VERSION=(NDK_ERR_APP_BASE-25);				/**固件补丁增量包版本不匹配*/
		public final int NDK_ERR_APP_CERT_HAS_EXPIRED=(NDK_ERR_APP_BASE-26);				/**证书已经失效*/
		public final int NDK_ERR_APP_CERT_NOT_YET_VALID=(NDK_ERR_APP_BASE-27);             /**证书尚未生效*/
		public final int NDK_ERR_APP_FILE_NAME_TOO_LONG=(NDK_ERR_APP_BASE-28);    /**文件名长度大�?32字节*/
		
	    public final int NDK_ERR_SECP_BASE = (-1000);
	    public final int NDK_ERR_SECP_TIMEOUT = (NDK_ERR_SECP_BASE - 1);             /**<获取键�?�超�?*/
	    public final int NDK_ERR_SECP_PARAM = (NDK_ERR_SECP_BASE - 2);               /**<输入参数非法*/
	    public final int NDK_ERR_SECP_DBUS = (NDK_ERR_SECP_BASE - 3);                /**<DBUS通讯错误*/
	    public final int NDK_ERR_SECP_MALLOC = (NDK_ERR_SECP_BASE - 4);              /**<动�?�内存分配错�?*/
	    public final int NDK_ERR_SECP_OPEN_SEC = (NDK_ERR_SECP_BASE - 5);            /**<打开安全设备错误*/
	    public final int NDK_ERR_SECP_SEC_DRV = (NDK_ERR_SECP_BASE - 6);             /**<安全设备操作错误*/
	    public final int NDK_ERR_SECP_GET_RNG = (NDK_ERR_SECP_BASE - 7);             /**<获取随机�?*/
	    public final int NDK_ERR_SECP_GET_KEY = (NDK_ERR_SECP_BASE - 8);             /**<获取密钥�?*/
	    public final int NDK_ERR_SECP_KCV_CHK = (NDK_ERR_SECP_BASE - 9);             /**<KCV校验错误*/
	    public final int NDK_ERR_SECP_GET_CALLER = (NDK_ERR_SECP_BASE - 10);         /**<获取调用者信息错�?*/
	    public final int NDK_ERR_SECP_OVERRUN = (NDK_ERR_SECP_BASE - 11);            /**<运行次数出错*/
	    public final int NDK_ERR_SECP_NO_PERMIT = (NDK_ERR_SECP_BASE - 12);          /**<权限不允�?*/
		public final int NDK_ERR_SECP_TAMPER = (NDK_ERR_SECP_BASE - 13);          	/**<安全攻击*/

	    public final int NDK_ERR_SECVP_BASE = (-1100);                           /**<未知错误*/
	    public final int NDK_ERR_SECVP_TIMEOUT = (NDK_ERR_SECVP_BASE - 1);       /**<获取键�?�超�?*/
	    public final int NDK_ERR_SECVP_PARAM = (NDK_ERR_SECVP_BASE - 2);         /**<输入参数非法*/
	    public final int NDK_ERR_SECVP_DBUS = (NDK_ERR_SECVP_BASE - 3);          /**<DBUS通讯错误*/
	    public final int NDK_ERR_SECVP_OPEN_EVENT0 =(NDK_ERR_SECVP_BASE - 4);   /**<打开event0设备出错*/
	    public final int NDK_ERR_SECVP_SCAN_VAL = (NDK_ERR_SECVP_BASE - 5);      /**<扫描值超出定�?*/
	    public final int NDK_ERR_SECVP_OPEN_RNG = (NDK_ERR_SECVP_BASE - 6);      /**<打开随机数设备错�?*/
	    public final int NDK_ERR_SECVP_GET_RNG = (NDK_ERR_SECVP_BASE - 7);       /**<获取随机数出�?*/
	    public final int NDK_ERR_SECVP_GET_ESC = (NDK_ERR_SECVP_BASE - 8);       /**<用户取消键�??�?*/
	    public final int NDK_ERR_SECVP_VPP = (-1120);                            /**<未知错误*/
	    public final int NDK_ERR_SECVP_INVALID_KEY=(NDK_ERR_SECVP_VPP);  		/**<无效密钥;内部使用.*/
		public final int NDK_ERR_SECVP_NOT_ACTIVE=(NDK_ERR_SECVP_VPP-1);  		/**<VPP没有�?活，第一次调用VPPInit.*/
		public final int NDK_ERR_SECVP_TIMED_OUT=(NDK_ERR_SECVP_VPP-2);			/**<已经超过VPP初始化的时间.*/
		public final int NDK_ERR_SECVP_ENCRYPT_ERROR=(NDK_ERR_SECVP_VPP-3);		/**<按确认键后，加密错误.*/
		public final int NDK_ERR_SECVP_BUFFER_FULL=(NDK_ERR_SECVP_VPP-4);		/**<输入BUF越界，（键入的PIN太长�?*/
		public final int NDK_ERR_SECVP_PIN_KEY=(NDK_ERR_SECVP_VPP-5);  			/**<数据键按下，回显"*".*/
		public final int NDK_ERR_SECVP_ENTER_KEY=(NDK_ERR_SECVP_VPP-6);			/**<确认键按下，PIN处理.*/
		public final int NDK_ERR_SECVP_BACKSPACE_KEY=(NDK_ERR_SECVP_VPP-7);		/**<�?格键按下.*/
		public final int NDK_ERR_SECVP_CLEAR_KEY=(NDK_ERR_SECVP_VPP-8);  		/**<清除键按下，清除�?�?'*'显示.*/
		public final int NDK_ERR_SECVP_CANCEL_KEY=(NDK_ERR_SECVP_VPP-9);  		/**<取消键被按下.*/
		public final int NDK_ERR_SECVP_GENERALERROR=(NDK_ERR_SECVP_VPP-10);  	/**<该进程无法继续�?�内部错�?.*/
		public final int NDK_ERR_SECVP_CUSTOMERCARDNOTPRESENT=(NDK_ERR_SECVP_VPP-11); /**<IC卡被拔出*/
		public final int NDK_ERR_SECVP_HTCCARDERROR=(NDK_ERR_SECVP_VPP-12);  	/**<访问智能卡错�?.*/
		public final int NDK_ERR_SECVP_WRONG_PIN_LAST_TRY=(NDK_ERR_SECVP_VPP-13);/**<智能�?-密码不正确，重试�?�?.*/
		public final int NDK_ERR_SECVP_WRONG_PIN=(NDK_ERR_SECVP_VPP-14); 		/**<智能�?-�?后尝试一�?.*/
		public final int NDK_ERR_SECVP_ICCERROR=(NDK_ERR_SECVP_VPP-15);  		/**<智能�?-重试太多�?*/
		public final int NDK_ERR_SECVP_PIN_BYPASS=(NDK_ERR_SECVP_VPP-16);  		/**<智能�?-PIN验证通过;并且PIN�?0长度*/
		public final int NDK_ERR_SECVP_ICCFAILURE=(NDK_ERR_SECVP_VPP-17);  		/**<智能�?-致命错误.*/
		public final int NDK_ERR_SECVP_GETCHALLENGE_BAD=(NDK_ERR_SECVP_VPP-18);  /**<智能�?-应答不是90 00.*/
		public final int NDK_ERR_SECVP_GETCHALLENGE_NOT8=(NDK_ERR_SECVP_VPP-19); /**<智能�?-无效的应答长�?.*/
	 	public final int NDK_ERR_SECVP_PIN_ATTACK_TIMER=(NDK_ERR_SECVP_VPP-20);  /**<PIN攻击定时器被�?�?*/

	    public final int NDK_ERR_SECCR_BASE = (-1200);                           /**<未知错误*/
	    public final int NDK_ERR_SECCR_TIMEOUT = (NDK_ERR_SECCR_BASE - 1);       /**<获取键�?�超�?*/
	    public final int NDK_ERR_SECCR_PARAM = (NDK_ERR_SECCR_BASE - 2);         /**<输入参数非法*/
	    public final int NDK_ERR_SECCR_DBUS = (NDK_ERR_SECCR_BASE - 3);          /**<DBUS通讯错误*/
	    public final int NDK_ERR_SECCR_MALLOC = (NDK_ERR_SECCR_BASE - 4);        /**<动�?�内存分配错�?*/
	    public final int NDK_ERR_SECCR_OPEN_RNG = (NDK_ERR_SECCR_BASE - 5);      /**<打开随机数设备错�?*/
	    public final int NDK_ERR_SECCR_DRV = (NDK_ERR_SECCR_BASE - 6);           /**<驱动加密错误*/
	    public final int NDK_ERR_SECCR_KEY_TYPE = (NDK_ERR_SECCR_BASE - 7);      /**<密钥类型错误*/
	    public final int NDK_ERR_SECCR_KEY_LEN = (NDK_ERR_SECCR_BASE - 8);       /**<密钥长度错误*/
	    public final int NDK_ERR_SECCR_GET_KEY = (NDK_ERR_SECCR_BASE - 9);       /**<获取密钥错误*/

	    public final int NDK_ERR_SECKM_BASE = (-1300);
	    public final int NDK_ERR_SECKM_TIMEOUT = (NDK_ERR_SECKM_BASE - 1);           /**<获取键�?�超�?*/
	    public final int NDK_ERR_SECKM_PARAM = (NDK_ERR_SECKM_BASE - 2);             /**<输入参数非法*/
	    public final int NDK_ERR_SECKM_DBUS = (NDK_ERR_SECKM_BASE - 3);              /**<DBUS通讯错误*/
	    public final int NDK_ERR_SECKM_MALLOC = (NDK_ERR_SECKM_BASE - 4);            /**<动�?�内存分配错�?*/
	    public final int NDK_ERR_SECKM_OPEN_DB = (NDK_ERR_SECKM_BASE - 5);           /**<数据库打�?错误*/
	    public final int NDK_ERR_SECKM_DEL_DB = (NDK_ERR_SECKM_BASE - 6);            /**<删除数据库错�?*/
	    public final int NDK_ERR_SECKM_DEL_REC = (NDK_ERR_SECKM_BASE - 7);           /**<删除记录错误*/
	    public final int NDK_ERR_SECKM_INSTALL_REC = (NDK_ERR_SECKM_BASE - 8);       /**<安装密钥记录错误*/
	    public final int NDK_ERR_SECKM_READ_REC = (NDK_ERR_SECKM_BASE - 9);          /**<读密钥记录错�?*/
	    public final int NDK_ERR_SECKM_OPT_NOALLOW = (NDK_ERR_SECKM_BASE - 10);      /**<操作不允�?*/
	    public final int NDK_ERR_SECKM_KEY_MAC = (NDK_ERR_SECKM_BASE - 11);          /**<密钥MAC校验错误*/
	    public final int NDK_ERR_SECKM_KEY_TYPE = (NDK_ERR_SECKM_BASE - 12);         /**<密钥类型错误*/
	    public final int NDK_ERR_SECKM_KEY_ARCH = (NDK_ERR_SECKM_BASE - 13);         /**<密钥体系错误*/
	    public final int NDK_ERR_SECKM_KEY_LEN  = (NDK_ERR_SECKM_BASE - 14);         /**<密钥长度错误*/
	    
	    
		public final int NDK_ERR_RFID_INITSTA=			-2005;  /**<非接触卡-射频接口器件故障或�?�未配置*/
		public final int NDK_ERR_RFID_NOCARD=			-2008;  /**<非接触卡-无卡  0x0D*/
		public final int NDK_ERR_RFID_MULTICARD=			-2009;  /**<非接触卡-多卡状�??*/
		public final int NDK_ERR_RFID_SEEKING=			-2010;  /**<非接触卡-寻卡/�?活过程中失败*/
		public final int NDK_ERR_RFID_PROTOCOL=			-2011;  /**<非接触卡-不支持ISO1444-4协议，如M1�?  F*/

		public final int NDK_ERR_RFID_NOPICCTYPE=		-2012;  /**<非接触卡-未设置卡 0x01*/
		public final int NDK_ERR_RFID_NOTDETE=			-2013;  /**<非接触卡-未寻�?   0x02*/
		public final int NDK_ERR_RFID_AANTI=				-2014;  /**<非接触卡-A卡冲�?(多张卡存�?)  0x03*/
		public final int NDK_ERR_RFID_RATS=				-2015;  /**<非接触卡-A卡RATS过程出错   0x04*/
		public final int NDK_ERR_RFID_BACTIV=			-2016;  /**<非接触卡-B卡激活失�?   0x07*/
		public final int NDK_ERR_RFID_ASEEK=				-2017;  /**<非接触卡-A卡寻卡失�?(可能多张卡存�?)   0x0A*/
		public final int NDK_ERR_RFID_BSEEK=				-2018;  /**<非接触卡-B卡寻卡失�?(可能多张卡存�?)   0x0B*/
		public final int NDK_ERR_RFID_ABON=				-2019;  /**<非接触卡-A、B卡同时存�?   0x0C*/
		public final int NDK_ERR_RFID_UPED=				-2020;  /**<非接触卡-已经�?�?(上电)   0x0E*/
		public final int NDK_ERR_RFID_NOTACTIV=			-2021;  /**<非接触卡-未激�?*/
		public final int NDK_ERR_RFID_COLLISION_A=       -2022;  /**<非接触卡-A卡冲�?*/
		public final int NDK_ERR_RFID__COLLISION_B=      -2023;  /**<非接触卡-B卡冲�?*/

		public final int NDK_ERR_MI_NOTAGERR=			-2030;  /**<非接触卡-无卡;				0xff*/
		public final int NDK_ERR_MI_CRCERR=				-2031;  /**<非接触卡-CRC�?;				0xfe*/
		public final int NDK_ERR_MI_EMPTY=				-2032;  /**<非接触卡-非空;				0xfd*/
		public final int NDK_ERR_MI_AUTHERR=				-2033;  /**<非接触卡-认证�?;			0xfc*/
		public final int NDK_ERR_MI_PARITYERR=			-2034;  /**<非接触卡-奇偶�?;			0xfb*/
		public final int NDK_ERR_MI_CODEERR=				-2035;  /**<非接触卡-接收代码�?			0xfa*/
		public final int NDK_ERR_MI_SERNRERR=            -2036;  /**<非接触卡-防冲突数据校验错	0xf8*/
		public final int NDK_ERR_MI_KEYERR=              -2037;  /**<非接触卡-认证KEY�?			0xf7*/
		public final int NDK_ERR_MI_NOTAUTHERR=          -2038;  /**<非接触卡-未认�?				0xf6*/
		public final int NDK_ERR_MI_BITCOUNTERR=         -2039;  /**<非接触卡-接收BIT�?			0xf5*/
		public final int NDK_ERR_MI_BYTECOUNTERR=        -2040;  /**<非接触卡-接收字节�?			0xf4*/
		public final int NDK_ERR_MI_WriteFifo=           -2041;  /**<非接触卡-FIFO写错�?			0xf3*/
		public final int NDK_ERR_MI_TRANSERR=            -2042;  /**<非接触卡-传�?�操作错�?		0xf2*/
		public final int NDK_ERR_MI_WRITEERR=            -2043;  /**<非接触卡-写操作错�?			0xf1*/
		public final int NDK_ERR_MI_INCRERR=				-2044;  /**<非接触卡-增量操作错误		0xf0*/
		public final int NDK_ERR_MI_DECRERR=             -2045;  /**<非接触卡-减量操作错误		0xef*/
		public final int NDK_ERR_MI_OVFLERR=             -2046;  /**<非接触卡-溢出错误			0xed*/
		public final int NDK_ERR_MI_FRAMINGERR=          -2047;  /**<非接触卡-帧错				0xeb*/
		public final int NDK_ERR_MI_COLLERR=             -2048;  /**<非接触卡-冲突				0xe8*/
		public final int NDK_ERR_MI_INTERFACEERR=        -2049;  /**<非接触卡-复位接口读写�?		0xe6*/
		public final int NDK_ERR_MI_ACCESSTIMEOUT=       -2050;  /**<非接触卡-接收超时			0xe5*/
		public final int NDK_ERR_MI_PROTOCOLERR=			-2051;  /**<非接触卡-协议�?				0xe4*/
		public final int NDK_ERR_MI_QUIT=                -2052;  /**<非接触卡-异常终止			0xe2*/
		public final int NDK_ERR_MI_PPSErr=				-2053;  /**<非接触卡-PPS操作�?			0xe1*/
		public final int NDK_ERR_MI_SpiRequest=			-2054;  /**<非接触卡-申请SPI失败		0xa0*/
		public final int NDK_ERR_MI_NY_IMPLEMENTED=		-2055;  /**<非接触卡-无法确认的错误状�?	0x9c*/
		public final int NDK_ERR_MI_CardTypeErr=			-2056;  /**<非接触卡-卡类型错			0x83*/
		public final int NDK_ERR_MI_ParaErrInIoctl=		-2057;  /**<非接触卡-IOCTL参数�?		0x82*/
		public final int NDK_ERR_MI_Para=				-2059;  /**<非接触卡-内部参数�?			0xa9*/

		// 蓝牙底座（wifi版本�?
		public final int NDK_ERR_WIFI_INVDATA=           -3001;  /**<WIFI>-无效参数*/
	    public final int NDK_ERR_WIFI_DEVICE_FAULT=      -3002;  /**<WIFI>-设备状�?�出�?*/
	    public final int NDK_ERR_WIFI_CMD_UNSUPPORTED=   -3003;  /**<WIFI>-不支持的命令*/
	    public final int NDK_ERR_WIFI_DEVICE_UNAVAILABLE=-3004;  /**<WIFI>-设备不可�?*/
	    public final int NDK_ERR_WIFI_DEVICE_NOTOPEN=    -3005;  /**<WIFI>-没有扫描到AP*/
	    public final int NDK_ERR_WIFI_DEVICE_BUSY=       -3006;  /**<WIFI>-设备�?*/
	    public final int NDK_ERR_WIFI_UNKNOWN_ERROR=     -3007;  /**<WIFI>-未知错误*/
	    public final int NDK_ERR_WIFI_PROCESS_INBADSTATE=-3008;  /**<WIFI>-无法连接到AP*/
	    public final int NDK_ERR_WIFI_SEARCH_FAULT=      -3009;  /**<WIFI>-扫描状�?�出�?*/
	    public final int NDK_ERR_WIFI_DEVICE_TIMEOUT=    -3010;  /**<WIFI>-设备超时*/
	    
	    public final int NDK_ERR_RFID_BUSY = -3101;                      /**<射频卡状态忙*/
	    public final int NDK_ERR_PRN_BUSY = -3102;                       /**<打印状�?�忙*/
	    public final int NDK_ERR_ICCARD_BUSY = -3103;                /**<IC卡状态忙*/
	    public final int NDK_ERR_MAG_BUSY = -3104;                       /**<磁卡状�?�忙*/
	    public final int NDK_ERR_USB_BUSY = -3105;                       /**<USB状�?�忙*/
	    public final int NDK_ERR_WLM_BUSY = -3106;                    /**<无线状�?�忙*/
	    public final int NDK_ERR_PIN_BUSY = -3107;					/**<正处于PIN输入状�??*/
	    
	    /**事件机制相关NDK返回�?*/
	    public final int NDK_ERR_EVENT_UNREGISTER     	= 	 -3301; /**事件未注�?*/
	    public final int NDK_EVENT_RFID_BUSY            = -3302;/**非接卡事件忙，非接设备被占用*/
	    public final int NDK_ERR_EVENT_UNREALIZED      	= -3303;	/**事件未注册，该功能不支持*/
	    
	    /**POSNDK�? Libnl_ndk.so的ERROR错误前缀*/
	    /**事件机制相关错误�?*/
	    public final int  NDK_ERR_POSNDK_BASE = -4000;              			 
	    public final int  NDK_ERR_POSNDK_BUSY= (NDK_ERR_POSNDK_BASE-1);       	 /**POSNDK硬件�?**/
	    public final int  NDK_ERR_POSNDK_TRANS_BUSY=(NDK_ERR_POSNDK_BASE-2);	 /**POSNDK事务�?*/
	    public final int  NDK_ERR_POSNDK_TRANS_ALREAY = (NDK_ERR_POSNDK_BASE-3); /**POSNDK已经在事务中*/
	    public final int  NDK_ERR_POSNDK_TRANS_NOEXIST = (NDK_ERR_POSNDK_BASE-4);/**POSNDK不在事务�?*/
	    public final int  NDK_ERR_POSNDK_SAFE_TRIGGER = (NDK_ERR_POSNDK_BASE-5); /**POSNDK硬件安全触发*/
	    
	    
	    public final int  NDK_ERR_POSNDK_VKB_INITERR = (NDK_ERR_POSNDK_BASE-17); /**虚拟键盘应用不存在或无法启动*/
	    public final int  NDK_ERR_POSNDK_VKB_DATAERR = (NDK_ERR_POSNDK_BASE-18); /**POSNDK虚拟键盘数据�?*/
	    
	    public final int  NDK_ERR_POSNDK_EVENT_NUM = (NDK_ERR_POSNDK_BASE-6);	 		/** 错误的事件号*/
	    public final int  NDK_ERR_POSNDK_EVENT_REG_TWICE = (NDK_ERR_POSNDK_BASE-7);		/** 重复注册事件*/
	    public final int  NDK_ERR_POSNDK_EVENT_UNREG_TWICE = (NDK_ERR_POSNDK_BASE-8);	/**并未注册*/
	    public final int  NDK_ERR_POSNDK_EVENT_INIT = (NDK_ERR_POSNDK_BASE-9);  		/**初始化错*/
	    public final int  NDK_ERR_POSNDK_EVENT_INUSE = (NDK_ERR_POSNDK_BASE-10); 		/**事件被其它进程占�?*/
	    
	    public final int  NDK_ERR_POSNDK_PERMISSION_UNDEFINED=(NDK_ERR_POSNDK_BASE-21);	/** POSNDK 权限未声�?*/
	    public final int  NDK_ERR_POSNDK_ACCESS_BUSY = (NDK_ERR_POSNDK_BASE-22);		/**POSNDK相关操作被其它进程占�?*/
	    /**libnl_ndk.so end*/
	    
	    /**自定义事件机制返回�??*/
	    public final int  NDK_NO_LISTENER_MAG = (NDK_ERR_POSNDK_BASE-23);		/**POSNDK 没有监听到mag事件*/
	    public final int  NDK_NO_LISTENER_ICC = (NDK_ERR_POSNDK_BASE-24);		/**POSNDK 没有监听到icc事件*/
	    public final int  NDK_NO_LISTENER_RFID = (NDK_ERR_POSNDK_BASE-25);		/**POSNDK 没有监听到rfid事件*/
	    public final int  NDK_NO_LISTENER_PIN = (NDK_ERR_POSNDK_BASE-26);		/**POSNDK 没有监听到pin事件*/
	    public final int  NDK_NO_LISTENER_PRINTER = (NDK_ERR_POSNDK_BASE-27);		/**POSNDK 没有监听到printer事件*/
	    

}
