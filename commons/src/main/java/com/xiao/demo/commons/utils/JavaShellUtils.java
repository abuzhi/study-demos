package com.xiao.demo.commons.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;

import java.io.File;
import java.io.IOException;

/**
 * Created by xiaoliang
 * 2017.04.11 11:07
 *
 * @Version 1.0
 */
public class JavaShellUtils {

    private String IP;
    private int PORT;
    private String USER;//登录用户名
    private String PASSWORD;//生成私钥的密码和登录密码，这两个共用这个密码
    private String PRIVATEKEY;// 本机的私钥文件
    private boolean usePassword = false;// 使用用户名和密码来进行登录验证。如果为true则通过用户名和密码登录，false则使用rsa免密码登录
    private Connection connection = null;

    public JavaShellUtils(String IP, int PORT, String USER, String PASSWORD, String PRIVATEKEY, boolean usePassword) throws Exception{
        this.IP = IP;
        this.PORT = PORT;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.PRIVATEKEY = PRIVATEKEY;
        this.usePassword = usePassword;

        connection = new Connection(IP, PORT);
    }

    /**
     * ssh用户登录验证，使用用户名和密码来认证
     * @param user
     * @param password
     * @return
     */
    public boolean isAuthedWithPassword(String user, String password) {
        try {
            return connection.authenticateWithPassword(user, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ssh用户登录验证，使用用户名、私钥、密码来认证 其中密码如果没有可以为null，生成私钥的时候如果没有输入密码，则密码参数为null
     *
     * @param user
     * @param privateKey
     * @param password
     * @return
     */
    public boolean isAuthedWithPublicKey(String user, File privateKey, String password) {
        try {
            return connection.authenticateWithPublicKey(user, privateKey, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAuth() {
        if (usePassword) {
            return isAuthedWithPassword(USER, PASSWORD);
        } else {
            return isAuthedWithPublicKey(USER, new File(PRIVATEKEY), PASSWORD);
        }
    }

    public void getFile(String remoteFile, String path) {
        try {
            connection.connect();
            boolean isAuthed = isAuth();
            if (isAuthed) {
                System.out.println("认证成功!");
                SCPClient scpClient = connection.createSCPClient();
                scpClient.get(remoteFile,path);
            } else {
                System.out.println("认证失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void putFile(String localFile, String remoteTargetDirectory,String mod) {
        try {
            connection.connect();
            boolean isAuthed = isAuth();
            if (isAuthed) {
                SCPClient scpClient = connection.createSCPClient();
                scpClient.put(localFile,remoteTargetDirectory,mod);
            } else {
                System.out.println("认证失败!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void executeShell(String shell){
        try {
            connection.connect();
            boolean isAuthed = isAuth();
            if (isAuthed) {
                Session session = connection.openSession();
                session.execCommand(shell);
                session.close();
            } else {
                System.out.println("认证失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
    }

}
