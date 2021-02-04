package proposal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    properties = {
            "proposal.proposalChildPojoBuilder.proposalValue=proposal-accepted",
            "proposal.proposalParentPojoBuilder.proposalChildPojoBuilder.proposalValue=proposal-accepted",
            "proposal.proposalParentPojoBuilderList[0].proposalChildPojoBuilder.proposalValue=proposal-accepted",
// TODO: this causes the ApplicationContext load to fail
//            "proposal.proposalParentPojoBuilderList[0].proposalChildPojosBuilderList[0].proposalValue=proposal-accepted",
    }
)
class ProposalPropertiesTest {
    @Autowired
    private ProposalProperties.Builder config;

    @Test
    void direct_child() {
        assertEquals("proposal-accepted", config.build().getProposalChildPojo().getProposalValue());
    }

    @Test
    void nested_child() {
        assertEquals("proposal-accepted", config.build().getProposalParentPojo().getProposalChildPojo().getProposalValue());
    }

    @Test
    void direct_list() {
        assertEquals("proposal-accepted", config.build().getProposalParentPojoList().get(0).getProposalChildPojo().getProposalValue());
    }

    @Test
    void nested_list() {
        assertEquals("proposal-accepted", config.build().getProposalParentPojoList().get(0).getProposalChildPojosList().get(0).getProposalValue());
    }
}
