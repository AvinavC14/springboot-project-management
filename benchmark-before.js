import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  vus: 50,
  duration: '30s',
};

export default function () {
  // login first to get token
  let loginRes = http.post('http://localhost:8080/auth/login',
    JSON.stringify({ username: 'testadmin', password: 'admin123' }),
    { headers: { 'Content-Type': 'application/json' } }
  );
  
  let token = JSON.parse(loginRes.body).token;

  let res = http.get('http://localhost:8080/SoftwareEngineers/getAll', {
    headers: { Authorization: `Bearer ${token}` }
  });

  check(res, { 'status 200': (r) => r.status === 200 });
  sleep(1);
}