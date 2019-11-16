package lab3.Observable;

import lab3.Config.WebSocketServer;
import lab3.Observer.Observer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RedGreenLight extends Observable {

    private Integer[] state = new Integer[]{1, 0, 0, 0};  // 东南西北红灯状态

    @Scheduled(cron = "0/30 * * * * ?")  // 定时每分钟执行一次
    public void UpdateState() {     // 更新并通知

        Integer index = 0;

        for (int i = 0; i < state.length; i++) {   // 更新红绿灯状态
            if (state[i] == 1) {

                if (i == state.length - 1) {
                    state[i] = 0;
                    state[0] = 1;
                    index = 0;
                    break;
                } else {
                    state[i] = 0;
                    state[i + 1] = 1;
                    index = i + 1;
                    break;
                }
            }
        }

        if (observers != null) {
            for (Observer i : observers) {
                i.call();
            }
        }

        System.out.println("当前方向：" + index.toString());

        try {
            WebSocketServer.sendInfo(index.toString(), null);   // 向前端推送消息
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Integer[] getState() {
        return state;
    }

}
