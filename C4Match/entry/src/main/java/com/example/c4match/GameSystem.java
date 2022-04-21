package com.example.c4match;

import com.example.c4match.slice.GameSystemSlice;
import com.example.c4match.utils.CommonData;
import com.example.c4match.utils.Log;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.event.commonevent.CommonEventData;
import ohos.event.commonevent.CommonEventManager;
import ohos.rpc.*;

public class GameSystem extends Ability {
    private static final String TAG = "GameSystem ";
    private GameRemote remote = new GameRemote();

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
//        super.setMainRoute(GameSystemSlice.class.getName());
    }

    @Override
    protected IRemoteObject onConnect(Intent intent) {
        super.onConnect(intent);
        return remote.asObject();
    }

    @Override
    public void onDisconnect(Intent intent) {
        Log.info(TAG+"PictureGameServiceAbility::onDisconnect");
    }

    private void sendEvent(int[] imageIndex, int moveImageId, int movePosition) {
        try {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder().withAction(CommonData.PICTURE_GAME_EVENT).build();
            intent.setOperation(operation);
            intent.setParam(CommonData.KEY_IMAGE_INDEX, imageIndex);
            intent.setParam(CommonData.KEY_MOVE_IMAGE_ID, moveImageId);
            intent.setParam(CommonData.KEY_MOVE_POSITION, movePosition);
            CommonEventData eventData = new CommonEventData(intent);
            CommonEventManager.publishCommonEvent(eventData);
        } catch (RemoteException e) {
            Log.error(TAG+"publishCommonEvent occur exception.");
        }
    }

    public class GameRemote extends RemoteObject implements IRemoteBroker{
        private static final int ERR_OK = 0;
        private static final int REQUEST_START_ABILITY = 1;

        private GameRemote(){
            super("GameRemote");
        }

        @Override
        public IRemoteObject asObject(){
            return this;
        }

        @Override
        public boolean onRemoteRequest(int code, MessageParcel data, MessageParcel reply, MessageOption option) {
            Log.info(TAG+"onRemoteRequest");
            int[] imageIndexs = data.readIntArray();
            String remoteDeviceId = data.readString();
            boolean isLocal = data.readBoolean();
            int moveImageId = data.readInt();
            int movePosition = data.readInt();
            Log.info(TAG+"Receive: "+ imageIndexs.length);
            reply.writeInt(ERR_OK);
            if (code == REQUEST_START_ABILITY) {
                Log.error(TAG + "RemoteServiceAbility::isFirstStart");
                Intent secondIntent = new Intent();
                Operation operation = new Intent.OperationBuilder().withDeviceId("")
                        .withBundleName(getBundleName())
                        .withAbilityName(CommonData.MAIN_ABILITY_CLASS_NAME)
                        .withAction(CommonData.PICTURE_PAGE)
                        .build();
                secondIntent.setParam(CommonData.KEY_REMOTE_DEVICEID, remoteDeviceId);
                secondIntent.setParam(CommonData.KEY_IS_LOCAL, isLocal);
                secondIntent.setParam(CommonData.KEY_MOVE_IMAGE_ID, moveImageId);
                secondIntent.setParam(CommonData.KEY_MOVE_POSITION, movePosition);
                secondIntent.setParam(CommonData.KEY_IMAGE_INDEX, imageIndexs);
                secondIntent.setOperation(operation);
                startAbility(secondIntent);
            } else {
                sendEvent(imageIndexs, moveImageId, movePosition);
            }
            return true;
        }
    }
}
