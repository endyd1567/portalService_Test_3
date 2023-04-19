package portalService.test2.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy{

    private Long id;

    public DeleteStatementStrategy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection con) throws SQLException {
        String sql = "delete from userinfo where id=? ";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setLong(1,id);
        return psmt;
    }
}
