import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GarageService {
  readonly API_URL = "http://localhost:8090";
  readonly ENDPOINTS_CARS = "/cars";

  constructor(private httpClient: HttpClient) {}

  getCars() {
    return this.httpClient.get(this.API_URL + this.ENDPOINTS_CARS);
  }
}
