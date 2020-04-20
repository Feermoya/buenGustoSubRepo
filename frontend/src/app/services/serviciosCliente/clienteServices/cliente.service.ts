import { HttpClient } from '@angular/common/http';
import { CommonService } from './../commonServices/common.service';
import { Injectable } from '@angular/core';
import { Cliente } from 'src/app/entidades/Cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService extends CommonService<Cliente>{

  _url = 'http://localhost:8080/api/cliente/';

  constructor(http: HttpClient) { super(http) }
}
