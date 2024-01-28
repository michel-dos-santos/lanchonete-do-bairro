import http from 'k6/http';
import { sleep } from 'k6';

export default (endpoint, getDuration, getReqs, getFailRate, getSuccessRate) => {
    let res = http.get(endpoint);

    getDuration.add(res.timings.duration);
    getReqs.add(1);
    getFailRate.add(res.status != 200);
    getSuccessRate.add(res.status == 200);

    sleep(1);
}
