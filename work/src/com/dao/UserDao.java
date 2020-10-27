package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;
import com.utils.DataBaseUtil;

public class UserDao {
    //��ѯ���ݿ���Ϣ

    /**
     * ���û��ύע����Ϣ�ǣ���Ҫ�жϸ��û����Ƿ����
     *
     * @param username
     * @return
     */
    public boolean userExist(String username) {
        Connection conn = DataBaseUtil.getConn();
        //����ָ�����û�����ѯ��Ϣ
        String sql = "select * from tb_user where username = ?";

        try {
            //��ȡPreparedStatement��������ִ�����ݿ��ѯ
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            //ִ�в�ѯ��ȡ�����
            ResultSet resultSet = preparedStatement.executeQuery();
            while (!resultSet.next()) {
                //���û�д����ݣ�֤�����û�������
                return true;
            }
            //�ͷ���Դ,�󴴽���������
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }

        return true;
    }

    /**
     * ���û��ύע����Ϣʱ�����ע��ɹ���Ҫ������Ҫ���û�ע�����Ϣ�������ݿ�
     */
    public void saveUser(User user) {
        //��ȡ���ݿ�����
        Connection conn = DataBaseUtil.getConn();
        //������Ϣ��sql���
        String sql = "insert into tb_user(username,password,sex,question,answer,email) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getQuestion());
            ps.setString(5, user.getAnswer());
            ps.setString(6, user.getEmail());
            //ִ�и��²���
            System.out.println(sql);
            ps.executeUpdate();
            //�ͷ���Դ
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ע��ɹ����û��ȿ�ͨ��ע����û���������е�¼�����ڳ�����ԣ��˲���ʵ���Ǹ���
     * �û����ṩ���û��������������ݿ���в�ѯ�������ѯ�ɹ������¼�ɹ�
     */
    public User login(String username, String password) {
        //ʵ����һ���û�����
        User user =null;
        Connection conn = DataBaseUtil.getConn();
        String sql = "select * from tb_user where username = ? and password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            //ִ�в�ѯ��ȡ�����
            ResultSet rs = ps.executeQuery();

            //�жϽ�����Ƿ���Ч,�����Ч������û����и�ֵ
            while (rs.next()) {

                user = new User();
                //���û�������и���
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getString("sex"));
                user.setQuestion(rs.getString("question"));
                user.setAnswer(rs.getString("answer"));
                user.setEmail(rs.getString("email"));
            }
            //�ͷ���Դ
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }

        return user;
    }
}

