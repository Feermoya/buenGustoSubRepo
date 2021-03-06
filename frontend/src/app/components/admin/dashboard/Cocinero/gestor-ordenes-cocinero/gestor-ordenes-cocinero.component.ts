import { AlertsService } from './../../../../../services/alertServices/alerts.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import Swal from 'sweetalert2';
import { EstadoPedido } from './../../../../../entidades/EstadoPedido';
import { EstadoPedidoServices } from './../../../../../services/serviciosCliente/estadoPedidoServices/estadoPedido.service';
import { PedidoServices } from './../../../../../services/serviciosCliente/pedidoServices/pedido.service';

import { Pedido } from './../../../../../entidades/Pedido';
import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/entidades/Cliente';

@Component({
  selector: 'app-gestor-ordenes-cocinero',
  templateUrl: './gestor-ordenes-cocinero.component.html',
  styleUrls: ['./gestor-ordenes-cocinero.component.css'],
})
export class GestorOrdenesCocineroComponent implements OnInit {
  public pedidos: Pedido[] = [];
  public pedidoOne: Pedido;
  pedidoSeleccionado: Pedido;
  public estados: EstadoPedido[] = [];
  public estadoSeleccionado: any;
  horaSeleccionada: Date;

  formularioEstado: FormGroup;
  public pageActual = 1; //Paginador

  public pedido: Pedido = {
    id: null,
    fechaRealizacion: new Date(),
    hora_estimada_fin: new Date(),
    numero: null,
    tipo_Envio: null,
    lista_detallePedido: [
      {
        id: null,
        articuloInsumo: null,
        articuloManufacturado: null,
        esInsumo: null,
        habilitado: null,
        cantidad: null,
        subtotal: null,
        aclaracion: '',
      },
    ],
    estadoPedido: {
      id: null,
      nombreEstado: '',
    },
    clientePedido: new Cliente(),
    minutosTotal: null,
    totalPedido: null,
    habilitado: null,
  };

  constructor(
    private pedidoService: PedidoServices,
    private estadoService: EstadoPedidoServices,
    private fb: FormBuilder,
    private alerts: AlertsService
  ) { }

  async ngOnInit() {
    await this.getAllPedidos();
    await this.getAllEstados();
    this.crearFormulario();
  }

  crearFormulario() {
    this.formularioEstado = this.fb.group({
      id: 0,
      nombreEstado: '',
    });
  }

  //Me trae los pedidos confirmados por el cajero
  getAllPedidos() {
    this.esperarAlert();
    this.pedidos.length = 0;
    this.pedidoService.getAll().subscribe(
      (res) => {
        console.log(res);
        res.filter((pedido) => {
          if (
            pedido.estadoPedido.nombreEstado === 'Confirmado' ||
            pedido.estadoPedido.nombreEstado === 'Preparando'
          ) {
            return this.pedidos.push(pedido);
          }
        });
        console.log(this.pedidos);
        Swal.fire({
          icon: 'success',
          title: 'Datos cargados',
          showConfirmButton: false,
          timer: 1200,
        });
      },
      (error) => {
        this.alerts.mensajeError(
          'Ocurrio un Error',
          'Vuelva a intentarlo mas tarde'
        );
        console.warn('error =>  ', error);
      }
    );
  }

  async getOnePedido(pedido) {
    this.pedidoOne = pedido;
    console.log('pedido one');
    console.log(this.pedidoOne);
  }

  atrasar10Min(pedidoARetrasar: Pedido, iterador) {
    Swal.fire({
      title: 'Seguro que desea retrasarlo?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Aceptar',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.value) {
        this.pedidoService.atrasarPor10Min(pedidoARetrasar.id).subscribe(
          (pedidoRetrasado) => {
            this.pedidos[iterador].hora_estimada_fin =
              pedidoRetrasado.hora_estimada_fin;
            console.log(pedidoRetrasado);
            Swal.fire('Pedido retrasado', '', 'success');
          },
          (erorr) => {
            Swal.fire('Error', 'No se pudo retrasar el pedido', 'error');
          }
        );
      } else {
        Swal.fire('Retraso cancelado', '', 'warning');
      }
    });
  }

  //Traer estados de pedido, traerme solo los confirmados
  getAllEstados() {
    this.estadoService.getAll().subscribe(
      (res) => {
        this.estados = res;
        // console.log(this.estados);
      },
      (error) => {
        console.warn('error =>  ', error);
      }
    );
  }

  //para setearle al pedido un estado
  seleccionarEstado(id: number, pedido: Pedido) {
    this.pedidoSeleccionado = pedido;
    this.estadoService.getOne(id).subscribe(
      (estado) => {
        Swal.fire({
          title: `Realmente desea cambiar el estado del pedido `,
          text:
            'Recuerda que una vez asignado un nuevo estado, no podras volver a cambiarlo!',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Si, modificar estado',
          cancelButtonText: 'Cancelar modificacion',
        }).then((result) => {
          if (result.value) {
            this.formularioEstado.setValue({
              id: estado.id,
              nombreEstado: estado.nombreEstado,
            });

            this.pedidoSeleccionado.estadoPedido = new EstadoPedido();
            this.pedidoSeleccionado.estadoPedido.id = this.formularioEstado.value.id;
            this.pedidoSeleccionado.estadoPedido.nombreEstado = this.formularioEstado.value.nombreEstado;
            this.pedidoService
              .editarEstadoPedido(
                this.pedidoSeleccionado.id,
                this.pedidoSeleccionado.estadoPedido
              )
              .subscribe((data) => {
                if (data.estadoPedido.nombreEstado === 'Listo') {
                  this.pedidoService
                    .descontarStock(this.pedidoSeleccionado.id)
                    .subscribe((pedidoDescontado) => {
                      console.log('Pedido descontado: ', pedidoDescontado);
                    });
                }
              });

            console.log(this.pedidoSeleccionado.estadoPedido.id);
            Swal.fire(
              `El estado de su pedido fue modificado correctamente a: `,
              'Puedes continuar con mas pedidos en la seccion de Estados de Pedidos!',
              'success'
            );
            this.formularioEstado.reset();
            this.getAllPedidos();
          } else {
            this.formularioEstado.reset();
          }
        });
      },
      (error) => {
        console.warn('error =>  ', error);
      }
    );
  }

  esperarAlert() {
    Swal.fire({
      title: 'Por favor espere',
      html: 'Recuperando los datos...',
      // timer: 1500,
      onBeforeOpen: () => {
        Swal.showLoading();
      },
    }).then((result) => {
      console.log(result);
    });
  }
}
