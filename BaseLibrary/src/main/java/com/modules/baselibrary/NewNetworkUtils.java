package com.modules.baselibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;


import com.socks.library.KLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 用于获取网络相关状态
 */
public class NewNetworkUtils {

    private final static String TAG = "NetworkUtils";

    /**
     * The Linux may have multiple wlans, such as wlan0,wlan1.
     */
    private final static String WLAN_PREFIX = "wlan";

    private final static String DEFAULT_IP = "0.0.0.0";

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

    public static NetworkType getNetworkType(Context context) {
        if (isEthernet(context)) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo info = getActiveNetworkInfo(context);
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


    private static NetworkInfo getActiveNetworkInfo(Context app) {
        ConnectivityManager cm =
                (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return null;
        return cm.getActiveNetworkInfo();
    }

    private static boolean isEthernet(Context app) {
        final ConnectivityManager cm =
                (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;
        final NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if (info == null) return false;
        NetworkInfo.State state = info.getState();
        if (null == state) return false;
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }


    /**
     * 利用发射判断是否打开了便携式热点，因为getWifiApState()是hide
     * {@link WifiManager#WIFI_AP_STATE_DISABLING} = 10,
     * {@link WifiManager#WIFI_AP_STATE_DISABLED} = 11,
     * {@link WifiManager#WIFI_AP_STATE_ENABLING} = 12,
     * {@link WifiManager#WIFI_AP_STATE_ENABLED} = 13,
     * {@link WifiManager#WIFI_AP_STATE_FAILED} = 14
     *
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
            return 13 == state;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getWifiIp(Context context) {
        String ip = DEFAULT_IP;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled()
                && wifiManager.getWifiState() == wifiManager.WIFI_STATE_ENABLED) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                int ipAddress = wifiInfo.getIpAddress();
                KLog.d(TAG, "WiFI ipAddress = " + ipAddress);
                if (ipAddress != 0) {
                    ip = ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff)
                            + "." + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
                }
                KLog.d(TAG, "WIFI IP = " + ip);
                return ip;
            }
        }
        return "";
    }


    /**
     * <p>
     * 回环地址（127.xxx.xxx.xxx）：127.0.0.0/8，为此目的保留IPv6地址:: 1/128。最常用的IPv4地址是127.0.0.1。通常，这些环回地址映射到主机名，localhost或loopback。
     * 链路地址（169.254.xxx.xxx）：IPv4链路本地地址定义在169.254.0.0/16地址块。 IPv6定义在fe80::/10地址块。
     * 私有地址(192.168.xxx.xxx,172.16.xxx.xxx,172.31.xxx.xxx,10.x.x.x):属于本地组织内部访问，只能在本地局域网可见。
     * 组播地址（224.xxx.xxx.xxx 到 239.xxx.xxx.xxx ）
     * 比较特殊的255.255.255.255 属于广播地址
     * 除此之外的地址就是点对点的可用的公开IPv4地址
     * https://www.cnblogs.com/starcrm/p/7071227.html
     * <p>
     * 如果是wifi，直接通过WifiInfo来获取ip
     * 如果是流量4g或者开启热点，通过遍历NetworkInterfaces，过滤掉所有wlan私有地址，回环地址，链路地址。
     *
     * @return 根据不同类型返回本地ip
     */
    public static String getLocalIp(Context context, boolean ip4) {

        NetworkType networkType = getNetworkType(context);
        boolean wifiApOpen = isWifiApOpen(context);
        KLog.d(TAG, "NetworkType=" + networkType + "----AP热点打开否=" + wifiApOpen);

        if (networkType == NetworkType.NETWORK_WIFI) {
            return getWifiIp(context);
        }

        try {

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            //备选InetAddress
            InetAddress candidateAddress = null;

            while (networkInterfaces.hasMoreElements()) {

                NetworkInterface networkInterface = networkInterfaces.nextElement();

                if (!networkInterface.isUp() || networkInterface.isLoopback()) {
                    continue;
                }

                KLog.d(TAG, "networkInterface===" + networkInterface.getName() + "---------DisplayName=" + networkInterface.getDisplayName());

                final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                //遍历该网卡下的所有ip，找到需要的
                while (inetAddresses.hasMoreElements()) {

                    final InetAddress inetAddress = inetAddresses.nextElement();

                    KLog.d("The inetAddress = " + inetAddress.toString());

                    //排除LoopbackAddress和LinkLocalAddress
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {

                        //开启ap的情况下，会有局域网ip，过滤掉它，但是可以记作此备选ip
                        if (networkInterface.getName().startsWith(WLAN_PREFIX)) {
                            candidateAddress = inetAddress;
                            KLog.d(TAG, "WLAN IP = " + inetAddress.getHostAddress());
                            continue;
                        }

                        //这就是我们要找的ip地址
                        if (inetAddress.isSiteLocalAddress()) {
                            String hostAddress = inetAddress.getHostAddress();
                            String ip = getIpFromHostAddress(ip4, hostAddress);
                            if (ip != null) return ip;
                        }

                        //如果没找到site local address并且candidateAddress也为空，那就用这个做备选
                        if (candidateAddress == null) {
                            candidateAddress = inetAddress;
                        }

                    }

                }
            }
            return getIpFromHostAddress(ip4, candidateAddress == null ? InetAddress.getLocalHost().getHostAddress() : candidateAddress.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DEFAULT_IP;

    }

    /**
     * ipv6 类似 fe80::2c73:2bff:fe0f:8ad8%dummy0
     *
     * @param ip4         是否返回ipv4
     * @param hostAddress
     * @return
     */
    public static String getIpFromHostAddress(boolean ip4, String hostAddress) {
        boolean isIPv4 = hostAddress.indexOf(58) < 0;
        if (ip4) {
            if (isIPv4) {
                KLog.d("获取到ipv4 address = " + hostAddress);
                return hostAddress;
            }
        } else if (!isIPv4) {
            int index = hostAddress.indexOf(37);
            String ipv6 = index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index).toUpperCase();
            KLog.d("获取到ipv6 address = " + hostAddress);
            return ipv6;
        }
        return DEFAULT_IP;
    }

//    public static String getHotspotIPAddress(Context context) {
//
//        int ipAddress = ((WifiManager)context.getSystemService(Context.WIFI_SERVICE)).getDhcpInfo().serverAddress;
//
//        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
//            ipAddress = Integer.reverseBytes(ipAddress);
//        }
//
//        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();
//
//        String ipAddressString = "";
//        try {
//            try {
//                ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//        } catch (Exception ex) {
//            ipAddressString = "";
//        }
//
//        return ipAddressString;
//
//    }


//    public static void getWifiApInfo(Context context) {
//        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//        DhcpInfo info=wifiManager.getDhcpInfo();
//        KLog.d(TAG,info);
//    }

//    /**
//     * 将ip的整数形式转换成ip形式
//     *
//     * @param ipInt
//     * @return
//     */
//    public static String int2ip(int ipInt) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(ipInt & 0xFF).append(".");
//        sb.append((ipInt >> 8) & 0xFF).append(".");
//        sb.append((ipInt >> 16) & 0xFF).append(".");
//        sb.append((ipInt >> 24) & 0xFF);
//        return sb.toString();
//    }
//
//    /**
//     * 获取当前ip地址
//     *
//     * @param context
//     * @return
//     */
//    public static String getLocalIpAddress(Context context) {
//        try {
//
//            WifiManager wifiManager = (WifiManager) context
//                    .getSystemService(Context.WIFI_SERVICE);
//            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//            int i = wifiInfo.getIpAddress();
//            KLog.d(TAG,int2ip(i));
//            return int2ip(i);
//        } catch (Exception ex) {
//            return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
//        }
//    }


//    public static String getLocalIpAddress() {
//        try {
//            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
//                NetworkInterface intf = en.nextElement();
//                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
//                    InetAddress inetAddress = enumIpAddr.nextElement();
////                    KLog.d("inetAddress===", inetAddress.toString());
////                    KLog.d("getHostAddress===", inetAddress.getHostAddress());
//                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
//                        return inetAddress.getHostAddress();
//                    }
//                }
//            }
//        } catch (SocketException ex) {
//            System.out.println("WifiPreference IpAddress" + ex.toString());
//        }
//        return null;
//    }

//    public static String getIPAddress(boolean useIPv4) {
//        try {
//            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
//            LinkedList adds = new LinkedList();
//
//            label64:
//            while (true) {
//                NetworkInterface ni;
//                do {
//                    do {
//                        if (!nis.hasMoreElements()) {
//                            Iterator var9 = adds.iterator();
//                            while (var9.hasNext()) {
//                                InetAddress add = (InetAddress) var9.next();
//                                if (!add.isLoopbackAddress() && !add.isLinkLocalAddress()) {
//                                    String hostAddress = add.getHostAddress();
//                                    boolean isIPv4 = hostAddress.indexOf(58) < 0;
//                                    if (useIPv4) {
//                                        if (isIPv4) {
//                                            return hostAddress;
//                                        }
//                                    } else if (!isIPv4) {
//                                        int index = hostAddress.indexOf(37);
//                                        return index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index).toUpperCase();
//                                    }
//                                }
//                            }
//                            break label64;
//                        }
//
//                        ni = nis.nextElement();
//                    } while (!ni.isUp());
//                } while (ni.isLoopback());
//
//                Enumeration addresses = ni.getInetAddresses();
//                while (addresses.hasMoreElements()) {
//                    adds.addFirst(addresses.nextElement());
//                }
//            }
//        } catch (SocketException var8) {
//            var8.printStackTrace();
//        }
//
//        return "";
//    }


}

