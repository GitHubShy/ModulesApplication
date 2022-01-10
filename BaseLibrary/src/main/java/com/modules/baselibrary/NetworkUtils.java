package com.modules.baselibrary;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;

public class NetworkUtils {

    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_5G,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    public static NetworkType getNetworkType(Application app) {
        if (isEthernet(app)) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo info = getActiveNetworkInfo(app);
        if (info != null && info.isAvailable()) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return NetworkType.NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return NetworkType.NETWORK_2G;

                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        return NetworkType.NETWORK_3G;

                    case TelephonyManager.NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return NetworkType.NETWORK_4G;

                    case 20:
                        return NetworkType.NETWORK_5G;
                    default:
                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            return NetworkType.NETWORK_3G;
                        } else {
                            return NetworkType.NETWORK_UNKNOWN;
                        }
                }
            } else {
                return NetworkType.NETWORK_UNKNOWN;
            }
        }
        return NetworkType.NETWORK_NO;
    }


    private static NetworkInfo getActiveNetworkInfo(Application app) {
        ConnectivityManager cm =
                (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return null;
        return cm.getActiveNetworkInfo();
    }

    private static boolean isEthernet(Application app) {
        final ConnectivityManager cm =
                (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;
        final NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if (info == null) return false;
        NetworkInfo.State state = info.getState();
        if (null == state) return false;
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            LinkedList adds = new LinkedList();

            label64:
            while (true) {
                NetworkInterface ni;
                do {
                    do {
                        if (!nis.hasMoreElements()) {
                            Iterator var9 = adds.iterator();
                            while (var9.hasNext()) {
                                InetAddress add = (InetAddress) var9.next();
                                if (!add.isLoopbackAddress() && !add.isLinkLocalAddress()) {
                                    String hostAddress = add.getHostAddress();
                                    boolean isIPv4 = hostAddress.indexOf(58) < 0;
                                    if (useIPv4) {
                                        if (isIPv4) {
                                            return hostAddress;
                                        }
                                    } else if (!isIPv4) {
                                        int index = hostAddress.indexOf(37);
                                        return index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index).toUpperCase();
                                    }
                                }
                            }
                            break label64;
                        }

                        ni = nis.nextElement();
                    } while (!ni.isUp());
                } while (ni.isLoopback());

                Enumeration addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    adds.addFirst(addresses.nextElement());
                }
            }
        } catch (SocketException var8) {
            var8.printStackTrace();
        }

        return "";
    }

    /**判断是否打开了便携式热点
     * {@link WifiManager#WIFI_STATE_DISABLED},
     {@link WifiManager#WIFI_STATE_DISABLING},
     {@link WifiManager#WIFI_STATE_ENABLED},
     {@link WifiManager#WIFI_STATE_ENABLING},
     {@link WifiManager#WIFI_STATE_UNKNOWN}
     * @param context
     * @return
     */
    public static boolean isWifiApOpen(Context context) {
        try {
            WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            //通过放射获取 getWifiApState()方法
            Method method = manager.getClass().getDeclaredMethod("getWifiApState");
            //调用getWifiApState() ，获取返回值
            int state = (int) method.invoke(manager);
            //判断是否开启
            if (WifiManager.WIFI_STATE_ENABLED == state) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    KLog.d("inetAddress===", inetAddress.toString());
//                    KLog.d("getHostAddress===", inetAddress.getHostAddress());
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("WifiPreference IpAddress" + ex.toString());
        }
        return null;
    }

    /**https://cloud.tencent.com/developer/article/1610919
     * https://blog.csdn.net/weixin_40911297/article/details/83619766
     * https://stackoverflow.com/questions/17302220/android-get-ip-address-of-a-hotspot-providing-device
     *
     * 回环地址（127.xxx.xxx.xxx）：127.0.0.0/8，为此目的保留IPv6地址:: 1/128。最常用的IPv4地址是127.0.0.1。通常，这些环回地址映射到主机名，localhost或loopback。
     * 链路地址（169.254.xxx.xxx）：IPv4链路本地地址定义在169.254.0.0/16地址块。 IPv6定义在fe80::/10地址块。
     * 私有地址(192.168.xxx.xxx,172.16.xxx.xxx,172.31.xxx.xxx):属于本地组织内部访问，只能在本地局域网可见。
     * 组播地址（224.xxx.xxx.xxx 到 239.xxx.xxx.xxx ）
     * 比较特殊的255.255.255.255 属于广播地址
     * 除此之外的地址就是点对点的可用的公开IPv4地址
     * https://www.cnblogs.com/starcrm/p/7071227.html
     * @return
     */
    public static String getLocalIp() {

        try {

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            //备选InetAddress
            InetAddress candidateAddress = null;

            while(networkInterfaces.hasMoreElements()) {

                NetworkInterface networkInterface = networkInterfaces.nextElement();

                LogProxy.e_simple("networkInterface===" + networkInterface.getName()+"---------DisplayName="+networkInterface.getDisplayName());

                final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {

                    final InetAddress inetAddress = inetAddresses.nextElement();

                    LogProxy.e_simple("The host address = " + inetAddress.getHostAddress());


                    //排除LoopbackAddress和LinkLocalAddress
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        if (inetAddress.isSiteLocalAddress()) {//是我们需要的ip地址,直接返回
                            return inetAddress.getHostAddress();
                        } else {
                            //如果不是siteLocalAddress,记住它作为候选
                            if (candidateAddress == null) {
                                candidateAddress = inetAddress;
                            }
                        }

                        LogProxy.e_simple("This is a loop back address = " + inetAddress.getHostAddress());
                    }

                }
            }

            return candidateAddress == null ? InetAddress.getLocalHost().getHostAddress() : candidateAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getHotspotIPAddress(Context context) {

        int ipAddress = ((WifiManager)context.getSystemService(Context.WIFI_SERVICE)).getDhcpInfo().serverAddress;

        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString = "";
        try {
            try {
                ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ipAddressString = "";
        }

        return ipAddressString;

    }


}
