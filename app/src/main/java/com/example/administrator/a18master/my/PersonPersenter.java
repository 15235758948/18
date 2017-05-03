package com.example.administrator.a18master.my;

import com.example.administrator.a18master.network.EasyShopApi;
import com.example.administrator.a18master.network.EasyShopClient;
import com.example.administrator.a18master.network.UICallBack;
import com.feicuiedu.apphx.model.HxMessageManager;
import com.feicuiedu.apphx.model.HxUserManager;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class PersonPersenter extends MvpNullObjectBasePresenter<PersonView>{

    private Call call;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call !=null) call.cancel();
    }

    //上传头像
    public void updataAvatar(File file){
        getView().showPrb();
        call = EasyShopClient.getInstance().uploadAvatar(file);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hidePrb();
                getView().showMsg(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                getView().hidePrb();
                UserResult userResult = new Gson().fromJson(body,UserResult.class);
                if (userResult == null){
                    getView().showMsg("未知错误");
                }else if (userResult.getCode() != 1){
                    getView().showMsg(userResult.getMessage());
                    return;
                }

                User user = userResult.getData();
                CachePreferences.setUser(user);

                //上传成功，触发ui操作（更新头像）
                getView().updataAvatar(userResult.getData().getHead_Image());

                //环信更新头像
                HxUserManager.getInstance().updateAvatar(EasyShopApi.IMAGE_URL + userResult.getData().getHead_Image());
                HxMessageManager.getInstance().sendAvatarUpdateMessage(EasyShopApi.IMAGE_URL + userResult.getData().getHead_Image());
            }
        });

    }
}
