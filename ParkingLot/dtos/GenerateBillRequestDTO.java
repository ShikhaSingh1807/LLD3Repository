package dtos;

public class GenerateBillRequestDTO
{
    private Long ticketId;
    private Long operatorId;

    public GenerateBillRequestDTO(Long ticketId, Long operatorId) {
        this.ticketId = ticketId;
        this.operatorId = operatorId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
