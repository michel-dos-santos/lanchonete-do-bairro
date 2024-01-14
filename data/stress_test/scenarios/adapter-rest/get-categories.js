import { Trend, Rate } from 'k6/metrics';
import Get from '../common.js';

export let getDuration = new Trend('get_categories_duration_adapter_rest');
export let getFailRate = new Rate('get_categories_fail_rate_adapter_rest');
export let getSuccessRate = new Rate('get_categories_success_rate_adapter_rest');
export let getReqs = new Rate('get_categories_reqs_adapter_rest');

export default GetCategories => {
    let endpoint = 'http://192.168.49.2:31081/api/v1/categories';
        
    Get(endpoint, getDuration, getReqs, getFailRate, getSuccessRate);
}
