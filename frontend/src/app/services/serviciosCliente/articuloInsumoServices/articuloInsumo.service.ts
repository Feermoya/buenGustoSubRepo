import { HttpClient } from '@angular/common/http';
import { CommonService } from '../commonServices/common.service';
import { Injectable } from '@angular/core';
import { ArticuloInsumo } from 'src/app/entidades/ArticuloInsumo';

@Injectable({
  providedIn: 'root',
})
export class ArticuloInsumoService extends CommonService<ArticuloInsumo> {
  _url = 'http://localhost:8080/api/articulo_insumo/';

  articulosInsumo: ArticuloInsumo[] = [];

  constructor(http: HttpClient) {
    super(http);
  }
}
