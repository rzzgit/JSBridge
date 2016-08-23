/**
 * Created by rzz on 2016/8/16.
 */
var JSBridgeCallBacks = {};
var TriggerNative;
function getCallID(){
    return 'JSBridge-'+new Date().getTime()+'-xxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
            return v.toString(16);
        });
}
function triggerNative(pluginName,method,data,success,error){
    var callID = getCallID();
    JSBridgeCallBacks[callID] = function(result){
        try{
            if(result.code == 200){
                success(result.data);
            }else{
                error(result.data);
            }
        }catch(e){
        }
        delete  JSBridgeCallBacks[callID];
    }
    if(TriggerNative){
        TriggerNative(pluginName,method,JSON.stringify(data),callID);
    }else{
        error('init bridge error');
    }
    //window.JSBridge.triggerNative(pluginName,method,JSON.stringify(data),callID);
    //window.webkit.messageHandlers.JSBridge.postMessage("aa","bbb");
}

