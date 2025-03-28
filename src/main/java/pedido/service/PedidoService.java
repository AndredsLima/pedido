package pedido.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pedido.dto.PedidoDTO;

@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private AmazonSQS amazonSQS;

    @Value("${aws.sqs.queue.url}")
    private String queueUrl;

    public boolean processarPedido(PedidoDTO pedidoDTO) {
        try {
            String mensagem = new ObjectMapper().writeValueAsString(pedidoDTO);
            amazonSQS.sendMessage(queueUrl, mensagem);
            return true;
        } catch (Exception e) {
            log.error("Erro ao enviar para SQS", e);
            return false;
        }
    }
}