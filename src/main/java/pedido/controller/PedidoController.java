package pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pedido.dto.PedidoDTO;
import pedido.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        boolean enviado = pedidoService.processarPedido(pedidoDTO);

        if (enviado) {
            return ResponseEntity.ok("Pedido criado e enviado para o sistema de estoque!");
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Serviço de estoque temporariamente indisponível");
        }
    }}