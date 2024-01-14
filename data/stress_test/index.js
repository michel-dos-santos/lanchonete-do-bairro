import GetHealthAdapterRest from "./scenarios/adapter-rest/get-healthcheck.js";
import GetCategoriesAdapterRest from "./scenarios/adapter-rest/get-categories.js";
import GetOrdersMonitorAdapterRest from "./scenarios/adapter-rest/get-orders-monitor.js";
import GetHealthAdapterConsumerBilling from "./scenarios/adapter-consumer-billing/get-healthcheck.js";
import { group } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";


export function handleSummary(data) {
  return {
    "summary.html": htmlReport(data),
  };
}

export const options = {
  vus: 2000,
  duration: '60s',
};

export default() =>{

  group('Adapter Rest', () => {
    GetHealthAdapterRest();
    GetCategoriesAdapterRest();
    GetOrdersMonitorAdapterRest();
  });

  group('Adapter Consumer Billing', () => {
    GetHealthAdapterConsumerBilling();
 });

}