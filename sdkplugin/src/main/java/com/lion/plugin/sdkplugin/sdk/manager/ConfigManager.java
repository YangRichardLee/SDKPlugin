package com.lion.plugin.sdkplugin.sdk.manager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Xml;

import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;
import com.lion.plugin.sdkplugin.sdk.modle.AdInfoEntity;
import com.lion.plugin.sdkplugin.sdk.modle.GetIdCardInfoEty;
import com.lion.plugin.sdkplugin.sdk.modle.Purchase;
import com.lion.plugin.sdkplugin.sdk.modle.RealNameInfoEntity;
import com.lion.plugin.sdkplugin.sdk.uitils.SharedPreferencesUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author zhangyang
 * @date 2017/12/11
 */

public class ConfigManager {

    private static final String TAG = ConfigManager.class.getName();

    private static Object object = new Object();
    private static ConfigManager mInstance;

    private static final String CONFIG_NAME = "GMConfig.xml";

    private static final String SDK_NODE_NAME = "gmsdk";

    private static final String SDK_ATTR_VERSION = "sdkVersion";

    private static final String SDK_ATTR_APPID = "appId";


    /**
     * jrtt 相关
     */
    private static final String SDK_JRTT_NAME = "jrtt";

    private static final String SDK_JRTT_APPNAME = "appName";

    private static final String SDK_JRTT_AID = "aId";

    private static final String SDK_JRTT_CHANNEL = "channel";

    /**
     * baidu相关
     */
    private static final String SDK_BAIDU_NAME = "baidu";

    private static final String SDK_BAIDU_LOG_ID = "uid";


    /**
     * 热云相关
     */

    private static final String SDK_REYUN_APPKEY = "appkey";


    private static final String SDK_REYUN_CHANNELID = "channelid";

    /**
     * 广点通相关
     */

    private static final String SDK_TC_ACTIONSETID = "ActionSetID";


    private static final String  SDK_TC_APPSECRETKEY = "AppSecretKey";
    /***
     * 拼多多相关
     */
    private static final String SDK_PDD_APPID = "appId";

    private static final String SDK_PDD_APPSECRET = "appSecret";
    //拼多多
    private String appIdPDD;
    private String appSecretPDD;


    //是否打开了拼多多
    private boolean hasPDD;

    //是否打开了快手
    private boolean hasKS;
    //快手
    private String appIDKS;
    private String appNameKS;

    //是否打开了UC
    private boolean hasUC;
    //UC
    private String sdkIdUC;
    private String appNameUC;
    private String appChannelUC;

    /**
     * 设备和服务器之间的时间差
     */
    private long mTimeDiff;

    /**
     * 游戏在平台申请的 APPID
     */
    private String mAppId;
    /**
     * sdk 版本号
     */
    private String mSdkVersion;
    /**
     * jrtt 所需appName
     */
    private String appName;
    /**
     * jrtt 所需channel
     */
    private String channel;
    /**
     * jrtt 所需appId
     */
    private String aId;

    private String baiduLogId;

    private boolean useFastLogin = true;
    private boolean jrttPay = false;

    public boolean isUseFastLogin() {
        return useFastLogin;
    }

    public void setUseFastLogin(boolean useFastLogin) {
        this.useFastLogin = useFastLogin;
    }

    public boolean isJrttPay() {
        return jrttPay;
    }

    public void setJrttPay(boolean jrttPay) {
        this.jrttPay = jrttPay;
    }

    /**
     * 实名控制
     */
    private GetIdCardInfoEty mGetIdCardInfoEty;

    public GetIdCardInfoEty getmGetIdCardInfoEty() {
        return mGetIdCardInfoEty;
    }

    public void setmGetIdCardInfoEty(GetIdCardInfoEty mGetIdCardInfoEty) {
        this.mGetIdCardInfoEty = mGetIdCardInfoEty;
    }

    /**
     * 热云 所需APPKEY
     */
    private String ryAppKey;

    /**
     * 热云所需channelId
     */
    private String ryChannelId;




    /**
     * 广点通需要的id
     */
    private String ActionSetID;

    /**
     * 广点通需要的key
     */
    private String AppSecretKey;


    public String getRyChannelId(Context context) {
        if (TextUtils.isEmpty(ryChannelId)) {
            ryChannelId = getAttrsValue(getNodeByName(context, "gmsdk/ry"), SDK_REYUN_CHANNELID);
        }
        return ryChannelId;
    }

    public String getRyAppKey(Context context) {
        if (TextUtils.isEmpty(ryAppKey)) {
            ryAppKey = getAttrsValue(getNodeByName(context, "gmsdk/ry"), SDK_REYUN_APPKEY);
        }
        return ryAppKey;
    }

    /**
     * 判断QQ登录使用网页还是APP  0:WEB  1:APP
     */
    private String QQLOGIN_WEB_OR_APP = "0";


    /**
     * 判断支付使用网页还是APP  0:WEB  1:APP
     */
    private String PAY_WEB_OR_APP = "0";

    private Element mRootElement;
    private HashMap<String, Boolean> map = new HashMap<>();
    //是否打开了今日
    private boolean mHasJrtt;
    //是否开启无水印模式
    private boolean mHasWsy;
    //是否打开了热云
    private boolean mHasRy;
    //是否打开了百度
    private boolean mHasBaidu;
    //是否打开了腾讯广点通
    private boolean mHasTc;

    private RealNameInfoEntity realNameInfoEntity;

    private String allOfPlayTime;

    private int mTipsTime;

    private int mStopGuest;

    private int mStopChild;

    private AdInfoEntity adInfoEntity;

    public AdInfoEntity getAdInfoEntity() {
        return adInfoEntity;
    }

    public void setAdInfoEntity(AdInfoEntity adInfoEntity) {
        this.adInfoEntity = adInfoEntity;
    }


    public int getmTipsTime() {
        return mTipsTime;
    }

    public void setmTipsTime(int mTipsTime) {
        this.mTipsTime = mTipsTime;
    }

    public int getmStopGuest() {
        return mStopGuest;
    }

    public void setmStopGuest(int mStopGuest) {
        this.mStopGuest = mStopGuest;
    }

    public int getmStopChild() {
        return mStopChild;
    }

    public void setmStopChild(int mStopChild) {
        this.mStopChild = mStopChild;
    }

    public String getAllOfPlayTime() {
        return allOfPlayTime;
    }

    public void setAllOfPlayTime(String allOfPlayTime) {
        this.allOfPlayTime = allOfPlayTime;
    }

    public RealNameInfoEntity getRealNameInfoEntity() {
        return realNameInfoEntity;
    }

    public void setRealNameInfoEntity(RealNameInfoEntity realNameInfoEntity) {
        this.realNameInfoEntity = realNameInfoEntity;
    }

    private Purchase mPurchase;

    public Purchase getmPurchase() {
        return mPurchase;
    }

    public void setmPurchase(Purchase mPurchase) {
        this.mPurchase = mPurchase;
    }

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (mInstance == null) {
            synchronized (ConfigManager.class) {
                if (mInstance == null) {
                    mInstance = new ConfigManager();
                }
            }
        }
        return mInstance;
    }

    public String getQQLOGIN_WEB_OR_APP() {
        return QQLOGIN_WEB_OR_APP;
    }

    public void setQQLOGIN_WEB_OR_APP(String QQLOGIN_WEB_OR_APP) {
        this.QQLOGIN_WEB_OR_APP = QQLOGIN_WEB_OR_APP;
    }

    public String getPAY_WEB_OR_APP() {
        return PAY_WEB_OR_APP;
    }

    public void setPAY_WEB_OR_APP(String PAY_WEB_OR_APP) {
        this.PAY_WEB_OR_APP = PAY_WEB_OR_APP;
    }


    /**
     * 获取设备和服务器的时间差
     *
     * @return
     */
    public long getTimeDiff() {
        return mTimeDiff;
    }

    /**
     * 设置时间差
     *
     * @param serverTime : 服务器时间
     */
    public void setTimeDiff(long serverTime) {
        this.mTimeDiff = serverTime - System.currentTimeMillis();
    }

    /**
     * 获取 appId
     *
     * @return
     */
    public String getAppId(Context context) {
        if (TextUtils.isEmpty(mAppId)) {
            mAppId = getValue(context, SDK_NODE_NAME, SDK_ATTR_APPID);
        }
        return mAppId;
    }

    /**
     * 获取 sdk 版本号
     *
     * @return
     */
    public String getSdkVersion(Context context) {
        if (TextUtils.isEmpty(mSdkVersion)) {
            mSdkVersion = getValue(context, SDK_NODE_NAME, SDK_ATTR_VERSION);
        }
        return mSdkVersion;
    }

    public String getAppName(Context context) {
        if (TextUtils.isEmpty(appName)) {
            appName = getAttrsValue(getNodeByName(context, "gmsdk/jrtt"), SDK_JRTT_APPNAME);
        }
        if (appName.isEmpty()){
            return "";
        }
        ULogUtil.d(TAG, "[getAppName...]  ： " + appName);
        return appName;
    }


    public String getBaiduLogId(Context context) {
        if (TextUtils.isEmpty(baiduLogId)) {
            baiduLogId = getAttrsValue(getNodeByName(context, "gmsdk/baidu"), SDK_BAIDU_LOG_ID);
        }
        return baiduLogId;
    }

    public String getAdInfo(Context context,String channel,String key) {
        return getAttrsValue(getNodeByName(context, channel), key);
    }




    /**
     * 获取指定节点下指定属性的值（NOTE：节点只能是MiliConfig下的第一级节点）
     *
     * @param nodeName  节点
     * @param attrsName 属性
     * @return String 返回属性值
     */
    private String getValue(Context context, String nodeName, String attrsName) {
        NodeList nodes = getRootElement(context).getElementsByTagName(nodeName);
        if (nodes == null || nodes.getLength() <= 0) {
            ULogUtil.w(TAG, "node is not exits, note name:" + nodeName);
            return "";
        }
        NamedNodeMap map = nodes.item(0).getAttributes();
        Node node = map.getNamedItem(attrsName);
        if (node != null) {
            return node.getNodeValue();
        }
        return "";
    }

    private Node getNodeByName(Context context, String path) {
        Element root = getRootElement(context);
        String[] nodeNameArr = path.split("/");
        if (nodeNameArr == null || nodeNameArr.length == 0) {
            ULogUtil.w(TAG, "node is not exits, note path:" + path);
            return null;
        }
        NodeList nodes = root.getElementsByTagName(nodeNameArr[0]);
        if (nodes == null || nodes.getLength() <= 0) {
            ULogUtil.w(TAG, "node is not exits, note name:" + path);
            return null;
        }
        Node tempNode = nodes.item(0);
        for (int i = 1; i < nodeNameArr.length; i++) {
            NodeList _nodes = tempNode.getChildNodes();
            if (_nodes == null || _nodes.getLength() <= 0) {
                ULogUtil.w(TAG, "node is not exits, note name:" + path);
                return null;
            }
            for (int j = 0; j < _nodes.getLength(); j++) {
                Node node = _nodes.item(j);
                ULogUtil.d(TAG, "node name:" + node.getNodeName());
                if (node.getNodeName().equals(nodeNameArr[i])) {
                    tempNode = node;
                    break;
                }
            }
        }
        return tempNode;
    }

    private String getAttrsValue(Node node, String attrsName) {
        ULogUtil.d(TAG, "[getAttrsValue ...] :  node name:" + node.getNodeName() + "   attrsName:" + attrsName);
        NamedNodeMap map = node.getAttributes();
        ULogUtil.d(TAG, "nameNodeMap is null ??" + map);
        Node valus = map.getNamedItem(attrsName);
        if (valus != null) {
            return valus.getNodeValue();
        }
        return "";
    }

    /**
     * 从GMConfig中读取显示的登陆方式
     */
    public static void requestLoginTypeByGMConfig(Context context) {
        String logintype = SharedPreferencesUtil.getString(SPConstants.GM_SDK_NEED_LOGIN_TYPES, "");
        if (logintype.isEmpty()) {

            String login;
            List<String> loginTypes = null;
            //传入文件名：GMConfig.xml；用来获取流
            try {
                InputStream is = context.getAssets().open("GMConfig.xml");
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(is, "UTF-8");// 设置数据源编码
                int eventType = parser.getEventType();// 获取事件类型
                ULogUtil.d(TAG, "[requestLoginTypeByGMConfig is start] ...  ");
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                            loginTypes = new ArrayList<String>();// 实例化集合类
                            break;
                        case XmlPullParser.START_TAG://开始读取某个标签
                            //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                            String name = parser.getName();
                            ULogUtil.d(TAG, "tag start>>>>> :" + parser.getName());
                            if (name.equalsIgnoreCase("login")) {
                                // 如果后面是Text元素,即返回它的值
                                login = parser.nextText();
                                loginTypes.add(login);
                                ULogUtil.d(TAG, "[login] ...  " + login);
                                ULogUtil.d(TAG, "[loginTypes.add(login)] ...  " + loginTypes.toString());
                            }
                            break;
                        case XmlPullParser.END_TAG:// 结束元素事件
                            //读完一个login，可以将其添加到集合类中
                            ULogUtil.d(TAG, "tag end <<<<<<  :" + parser.getName());
                            break;
                    }
                    eventType = parser.next();
                }
                is.close();

            } catch (XmlPullParserException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            SharedPreferencesUtil.saveString(SPConstants.GM_SDK_NEED_LOGIN_TYPES, loginTypes.toString());
            ULogUtil.d(TAG, "[requestLoginTypeByGMConfig  loginTypes] ...  :" + loginTypes.toString());
        }
    }

    /**
     * 获取RootElement
     */
    private Element getRootElement(Context context) {
        if (mRootElement != null) {
            return mRootElement;
        }
        try {
            InputStream mXmlResourceParser = context.getAssets().open(CONFIG_NAME);
            DocumentBuilder builder = null;
            DocumentBuilderFactory factory = null;
            factory = DocumentBuilderFactory.newInstance();
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e1) {
                ULogUtil.e(TAG, "read ConfigXml factory创建失败", e1);
            }
            try {
                Document document = builder.parse(mXmlResourceParser);
                mRootElement = document.getDocumentElement();
            } catch (SAXException e) {
                ULogUtil.e(TAG, "解析xml失败", e);
            }
        } catch (IOException e) {
            ULogUtil.e(TAG, "解析xml失败", e);
        }
        return mRootElement;
    }

}
