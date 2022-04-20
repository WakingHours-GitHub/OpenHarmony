package com.example.c4match;

import com.example.c4match.slice.SutdySystemSlice;
import com.example.c4match.utils.CommonData;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.OnClickListener;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.AdaptiveBoxLayout;
import ohos.agp.render.Region;
import ohos.event.commonevent.CommonEventData;
import ohos.event.commonevent.CommonEventManager;
import ohos.media.image.PixelMap;
import ohos.rpc.*;

import java.lang.annotation.Target;
import java.security.PrivateKey;

public class StudySystem extends Ability {
    private final String TAG = this.getClass().getName();

    private StudyRemote remote = new StudyRemote();


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SutdySystemSlice.class.getName());
    }


    private void sendEvent(boolean[] isLastPoint, float[] points_x, float[] points_y){
        Log.info(TAG+"sendEvent");
        try{
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withAction(CommonData.MATH_DRAW_EVENT)
                    .build();
            intent.setParam(CommonData.KEY_POINT_X, points_x);
            intent.setParam(CommonData.KEY_POINT_Y, points_y);
            intent.setParam(CommonData.KEY_IS_LAST_POINT, isLastPoint);
            CommonEventData eventData = new CommonEventData(intent);
            CommonEventManager.publishCommonEvent(eventData);

        }catch (RemoteException e){
            Log.error(TAG+"publishCommonEvent occur exception.");
        }
    }





    public class StudyRemote extends RemoteObject implements IRemoteBroker{
        private static final int ERR_OK = 0;

        public StudyRemote() {
            super("StudyRemote");
        }

        @Override
        public IRemoteObject asObject() {
            return this;
        }

        @Override
        public boolean onRemoteRequest(int code, MessageParcel data, MessageParcel reply, MessageOption option){
            Log.info(TAG+"onRemoteRequest");
            float[] points_x = data.readFloatArray();
            float[] points_y = data.readFloatArray();

            boolean[] isLastPoint = data.readBooleanArray();
            reply.writeInt(ERR_OK);
            sendEvent(isLastPoint, points_x, points_y);

            return true;


        }


    }
}
