import { Trend, Rate } from 'k6/metrics';
import Get from './../common.js';

export let getHealthDuration = new Trend('get_health_duration_adapter_consumer_billing');
export let getHealthFailRate = new Rate('get_health_fail_rate_adapter_consumer_billing');
export let getHealthSuccessRate = new Rate('get_health_success_rate_adapter_consumer_billing');
export let getHealthReqs = new Rate('get_health_reqs_adapter_consumer_billing');

export default GetHealth => {
    let endpoint = 'http://192.168.49.2:31082/api/actuator/health';
        
    Get(endpoint, getHealthDuration, getHealthReqs, getHealthFailRate, getHealthSuccessRate);
}
