package net.comorevi.cpapp.chestshop;

import cn.nukkit.utils.TextFormat;
import net.comorevi.cphone.cphone.CPhone;
import net.comorevi.cphone.cphone.application.ApplicationManifest;
import net.comorevi.cphone.cphone.model.Bundle;
import net.comorevi.cphone.cphone.model.ModalResponse;
import net.comorevi.cphone.cphone.model.Response;
import net.comorevi.cphone.cphone.widget.activity.ReturnType;
import net.comorevi.cphone.cphone.widget.activity.base.ModalActivity;
import net.comorevi.np.moneys.chestshop.util.DataCenter;

public class MainActivity extends ModalActivity {

    private Bundle bundle;
    private CPhone cPhone;

    public MainActivity(ApplicationManifest manifest) {
        super(manifest);
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.bundle = bundle;
        this.cPhone = bundle.getCPhone();
        this.setTitle(bundle.getString("title"));
        this.setContent(bundle.getString("content"));
        this.setButton1Text(bundle.getString("button1"));
        this.setButton2Text(bundle.getString("button2"));
    }

    @Override
    public ReturnType onStop(Response response) {
        ModalResponse modalResponse = (ModalResponse) response;
        if (modalResponse.isButton1Clicked()) {
            if (!DataCenter.existsEditCmdQueue(modalResponse.getPlayer())) {
                DataCenter.addEditCmdQueue(modalResponse.getPlayer());
                modalResponse.getPlayer().sendMessage(TextFormat.GRAY + bundle.getString("prefix") + TextFormat.RESET + bundle.getString("enabled"));
            } else {
                DataCenter.removeEditCmdQueue(modalResponse.getPlayer());
                modalResponse.getPlayer().sendMessage(TextFormat.GRAY + bundle.getString("prefix") + TextFormat.RESET + bundle.getString("disabled"));
            }
            return ReturnType.TYPE_IGNORE;
        } else {
            cPhone.setHomeMessage(bundle.getString("cphone_home"));
            return ReturnType.TYPE_END;
        }
    }
}
