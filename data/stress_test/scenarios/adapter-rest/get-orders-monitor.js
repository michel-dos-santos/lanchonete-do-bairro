import { Trend, Rate } from 'k6/metrics';
import Get from '../common.js';

export let getDuration = new Trend('get_orders_monitor_duration_adapter_rest');
export let getFailRate = new Rate('get_orders_monitor_fail_rate_adapter_rest');
export let getSuccessRate = new Rate('get_orders_monitor_success_rate_adapter_rest');
export let getReqs = new Rate('get_orders_monitor_reqs_adapter_rest');

export default GetOrdersMonitor => {
    let endpoint = 'http://192.168.49.2:31081/api/v1/orders/monitor';
        
    Get(endpoint, getDuration, getReqs, getFailRate, getSuccessRate);
}
