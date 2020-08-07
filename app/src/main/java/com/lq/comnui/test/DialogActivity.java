package com.lq.comnui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.lq.comn.BaseDialog;
import com.lq.comnui.R;
import com.lq.comnui.bean.SelectBean;
import com.lq.comnui.dialog.AddressDialog;
import com.lq.comnui.dialog.DateDialog;
import com.lq.comnui.dialog.InputDialog;
import com.lq.comnui.dialog.MenuDialog;
import com.lq.comnui.dialog.MessageDialog;
import com.lq.comnui.dialog.PayPasswordDialog;
import com.lq.comnui.dialog.SelectDialog;
import com.lq.comnui.dialog.TimeDialog;
import com.lq.comnui.dialog.ToastDialog;
import com.lq.comnui.dialog.WaitDialog;
import com.lq.comnui.popup.MenuPopup;
import com.lq.comnui.util.ComnToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton btn_dialog_message; //消息
    AppCompatButton btn_dialog_input; //输入
    AppCompatButton btn_dialog_menu; //底部选择
    AppCompatButton btn_dialog_center_menu; //居中选择
    AppCompatButton btn_dialog_single_select; // 单选对话框
    AppCompatButton btn_dialog_more_select; //多选对话框
    AppCompatButton btn_dialog_succeed_toast; //成功对话框
    AppCompatButton btn_dialog_fail_toast; //失败对话框
    AppCompatButton btn_dialog_warn_toast; //警告对话框
    AppCompatButton btn_dialog_wait; //等待对话框
    AppCompatButton btn_dialog_pay; //支付密码
    AppCompatButton btn_dialog_address; //地区选择
    AppCompatButton btn_dialog_date; //日期选择
    AppCompatButton btn_dialog_time; //时间选择
    AppCompatButton btn_dialog_custom; //自定义

    AppCompatButton btn_dialog_pop; //Pop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
    }

    private void initView(){
        btn_dialog_message = findViewById(R.id.btn_dialog_message);
        btn_dialog_input = findViewById(R.id.btn_dialog_input);
        btn_dialog_menu = findViewById(R.id.btn_dialog_menu);
        btn_dialog_center_menu = findViewById(R.id.btn_dialog_center_menu);
        btn_dialog_single_select = findViewById(R.id.btn_dialog_single_select);
        btn_dialog_more_select = findViewById(R.id.btn_dialog_more_select);
        btn_dialog_succeed_toast = findViewById(R.id.btn_dialog_succeed_toast);
        btn_dialog_fail_toast = findViewById(R.id.btn_dialog_fail_toast);
        btn_dialog_warn_toast = findViewById(R.id.btn_dialog_warn_toast);
        btn_dialog_wait = findViewById(R.id.btn_dialog_wait);
        btn_dialog_pay = findViewById(R.id.btn_dialog_pay);
        btn_dialog_address = findViewById(R.id.btn_dialog_address);
        btn_dialog_date = findViewById(R.id.btn_dialog_date);
        btn_dialog_time = findViewById(R.id.btn_dialog_time);
        btn_dialog_custom = findViewById(R.id.btn_dialog_custom);
        btn_dialog_pop = findViewById(R.id.btn_dialog_pop);

        btn_dialog_message.setOnClickListener(this);
        btn_dialog_input.setOnClickListener(this);
        btn_dialog_menu.setOnClickListener(this);
        btn_dialog_center_menu.setOnClickListener(this);
        btn_dialog_single_select.setOnClickListener(this);
        btn_dialog_more_select.setOnClickListener(this);
        btn_dialog_succeed_toast.setOnClickListener(this);
        btn_dialog_fail_toast.setOnClickListener(this);
        btn_dialog_warn_toast.setOnClickListener(this);
        btn_dialog_wait.setOnClickListener(this);
        btn_dialog_pay.setOnClickListener(this);
        btn_dialog_address.setOnClickListener(this);
        btn_dialog_date.setOnClickListener(this);
        btn_dialog_time.setOnClickListener(this);
        btn_dialog_custom.setOnClickListener(this);
        btn_dialog_pop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_bar: //设置状态栏是否为浅色模式
                startActivity(new Intent(this,BarActivity.class));
                break;
            case R.id.btn_dialog_message:
                // 消息对话框
                new MessageDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle("我是标题")
                        // 内容必须要填写
                        .setMessage("我是内容")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new MessageDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog) {
                                ComnToast.showMsg("点击了确定");
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("点击了取消");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_input:
                // 输入对话框
                new InputDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle("我是标题")
                        // 内容可以不用填写
//                        .setContent("我是内容")
                        // 提示可以不用填写
                        .setHint("我是提示")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                ComnToast.showMsg("点击了确定");
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("点击了取消");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_menu:
                // 底部选择框
                List<SelectBean> selectBeanList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    SelectBean selectBean = new SelectBean();
                    selectBean.selectId = i + 10;
                    selectBean.selectTitle = "点击Item--" + i;
                    selectBeanList.add(selectBean);
                }
                // 底部选择框
                new MenuDialog.Builder(this)
                        // 设置 null 表示不显示取消按钮
                        //.setCancel(getString(R.string.common_cancel))
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setList(selectBeanList)
                        .setListener(new MenuDialog.OnListener<SelectBean>() {

                            @Override
                            public void onSelected(BaseDialog dialog, int position, SelectBean bean) {
                                ComnToast.showMsg("位置：" + bean.selectId + "，文本：" + bean.selectTitle);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("点击了取消");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_center_menu:
                // 居中选择框
                List<SelectBean> selectList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    SelectBean selectBean = new SelectBean();
                    selectBean.selectId = i + 10;
                    selectBean.selectTitle = "点击Item--" + i;
                    selectList.add(selectBean);
                }
                // 居中选择框
                new MenuDialog.Builder(this)
                        .setGravity(Gravity.CENTER)
                        // 设置 null 表示不显示取消按钮
                        //.setCancel(null)
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setList(selectList)
                        .setListener(new MenuDialog.OnListener<SelectBean>() {

                            @Override
                            public void onSelected(BaseDialog dialog, int position, SelectBean bean) {
                                ComnToast.showMsg("位置：" + bean.selectId + "，文本：" + bean.selectTitle);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("点击了取消");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_single_select:
                // 单选对话框
                new SelectDialog.Builder(this)
                        .setTitle("请选择你的性别")
                        .setList("男", "女")
                        // 设置单选模式
                        .setSingleSelect()
                        // 设置默认选中
                        .setSelect(0)
                        .setListener(new SelectDialog.OnListener<String>() {

                            @Override
                            public void onSelected(BaseDialog dialog, HashMap<Integer, String> data) {
                                ComnToast.showMsg("确定了" + data.toString());
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_more_select:
                // 多选对话框
                new SelectDialog.Builder(this)
                        .setTitle("请选择工作日")
                        .setList("星期一", "星期二", "星期三", "星期四", "星期五")
                        // 设置最大选择数
                        .setMaxSelect(3)
                        // 设置默认选中
                        .setSelect(2, 3, 4)
                        .setListener(new SelectDialog.OnListener<String>() {

                            @Override
                            public void onSelected(BaseDialog dialog, HashMap<Integer, String> data) {
                                ComnToast.showMsg("确定了" + data.toString());
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_succeed_toast:
                // 成功对话框
                new ToastDialog.Builder(this)
                        .setType(ToastDialog.Type.FINISH)
                        .setMessage("完成")
                        .show();
                break;
            case R.id.btn_dialog_fail_toast:
                // 失败对话框
                new ToastDialog.Builder(this)
                        .setType(ToastDialog.Type.ERROR)
                        .setMessage("错误")
                        .show();
                break;
            case R.id.btn_dialog_warn_toast:
                // 警告对话框
                new ToastDialog.Builder(this)
                        .setType(ToastDialog.Type.WARN)
                        .setMessage("警告")
                        .show();
                break;
            case R.id.btn_dialog_wait:
                // 等待对话框
                final BaseDialog waitDialog = new WaitDialog.Builder(this)
                        // 消息文本可以不用填写
                        .setMessage("加载中...")
                        .show();

//                waitDialog.dismiss();
//                postDelayed(waitDialog::dismiss, 2500);
                break;
            case R.id.btn_dialog_pay:
                // 支付密码输入对话框
                new PayPasswordDialog.Builder(this)
                        .setTitle("请输入支付密码")
                        .setSubTitle("用于购买一个女盆友")
                        .setMoney("￥ 100.00")
                        //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                        .setListener(new PayPasswordDialog.OnListener() {

                            @Override
                            public void onCompleted(BaseDialog dialog, String password) {
                                ComnToast.showMsg(password);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_address:
                // 选择地区对话框
                new AddressDialog.Builder(this)
                        .setTitle("请选择地区")
                        // 设置默认省份
                        //.setProvince("广东省")
                        // 设置默认城市（必须要先设置默认省份）
                        //.setCity("广州市")
                        // 不选择县级区域
                        //.setIgnoreArea()
                        .setListener(new AddressDialog.OnListener() {

                            @Override
                            public void onSelected(BaseDialog dialog, String province, String city, String area) {
                                ComnToast.showMsg(province + city + area);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_date:
                // 日期选择对话框
                new DateDialog.Builder(this)
                        .setTitle("请选择日期")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置日期
                        //.setDate("2018-12-31")
                        //.setDate("20181231")
                        //.setDate(1546263036137)
                        // 设置年份
                        //.setYear(2018)
                        // 设置月份
                        //.setMonth(2)
                        // 设置天数
                        //.setDay(20)
                        // 不选择天数
                        //.setIgnoreDay()
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int year, int month, int day) {
                                ComnToast.showMsg(year + "年" + month + "月" + day + "日");

                                // 如果不指定时分秒则默认为现在的时间
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                // 月份从零开始，所以需要减 1
                                calendar.set(Calendar.MONTH, month - 1);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                ComnToast.showMsg("时间戳：" + calendar.getTimeInMillis());
                                //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_time:
                // 时间选择对话框
                new TimeDialog.Builder(this)
                        .setTitle("请选择时间")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置时间
                        //.setTime("23:59:59")
                        //.setTime("235959")
                        // 设置小时
                        //.setHour(23)
                        // 设置分钟
                        //.setMinute(59)
                        // 设置秒数
                        //.setSecond(59)
                        // 不选择秒数
                        //.setIgnoreSecond()
                        .setListener(new TimeDialog.OnListener() {

                            @Override
                            public void onSelected(BaseDialog dialog, int hour, int minute, int second) {
                                ComnToast.showMsg(hour + "时" + minute + "分" + second + "秒");

                                // 如果不指定年月日则默认为今天的日期
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                calendar.set(Calendar.MINUTE, minute);
                                calendar.set(Calendar.SECOND, second);
                                ComnToast.showMsg("时间戳：" + calendar.getTimeInMillis());
                                //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                ComnToast.showMsg("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_custom:
                // 自定义对话框
                new BaseDialog.Builder(this)
                        .setContentView(R.layout.dialog_custom)
                        //.setText(id, "我是预设置的文本")
                        .setOnClickListener(R.id.btn_dialog_custom_ok, (dialog, view1) -> dialog.dismiss())
                        .addOnShowListener(dialog -> ComnToast.showMsg("Dialog  显示了"))
                        .addOnCancelListener(dialog -> ComnToast.showMsg("Dialog 取消了"))
                        .addOnDismissListener(dialog -> ComnToast.showMsg("Dialog 销毁了"))
                        .setOnKeyListener((dialog, event) -> {
                            ComnToast.showMsg("按键代码：" + event.getKeyCode());
                            return false;
                        })
                        .show();
                break;
            case R.id.btn_dialog_pop:
                // 菜单弹窗
                new MenuPopup.Builder(this)
                        .setList("选择拍照", "选取相册")
                        .setListener((MenuPopup.OnListener<String>) (popupWindow, position, s) -> ComnToast.showMsg(s))
                        .showAsDropDown(btn_dialog_pop);
                break;
        }
    }
}